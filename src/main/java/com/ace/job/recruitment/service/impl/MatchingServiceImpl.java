package com.ace.job.recruitment.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.job.recruitment.dto.MatchingConfig;
import com.ace.job.recruitment.dto.MatchingResult;
import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.entity.MatchingRule;
import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.repository.ClinicalTrialRepository;
import com.ace.job.recruitment.repository.MatchingRuleRepository;
import com.ace.job.recruitment.repository.UserRepository;
import com.ace.job.recruitment.service.MatchingService;
import com.ace.job.recruitment.service.SubjectService;
import com.ace.job.recruitment.util.TextNormalizer;

/**
 * 智能匹配服务实现类（优化版）
 * 实现核心的匹配算法，支持：
 * 1. 硬性约束条件（年龄、性别、排除条件）
 * 2. 多维度评分（年龄、健康状况、优先条件）
 * 3. 可配置权重
 * 4. 文本标准化和近义词匹配
 */
@Service
public class MatchingServiceImpl implements MatchingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClinicalTrialRepository clinicalTrialRepository;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private MatchingRuleRepository matchingRuleRepository;

	/**
	 * 计算受试者与临床试验的匹配分数（使用默认配置）
	 */
	@Override
	public int calculateMatchingScore(User subject, ClinicalTrial trial) {
		return calculateMatchingScore(subject, trial, new MatchingConfig());
	}

	/**
	 * 计算受试者与临床试验的匹配分数（带配置）
	 * 返回详细的评分信息
	 * 
	 * @param subject 受试者
	 * @param trial   临床试验
	 * @param config  匹配配置
	 * @return MatchingResult 包含分数和详细评分信息
	 */
	public MatchingResult calculateMatchingScoreWithDetails(User subject, ClinicalTrial trial, MatchingConfig config) {
		MatchingResult.ScoreDetails details = new MatchingResult.ScoreDetails();
		
		// ========== 第一阶段：硬性约束检查 ==========
		
		// 验证受试者数据完整性
		if (subject.getAge() == null || subject.getGender() == null) {
			return new MatchingResult(subject, 0, "数据不完整", details);
		}

		// 1. 年龄范围检查
		if (subject.getAge() < trial.getAgeMin() || subject.getAge() > trial.getAgeMax()) {
			String reason = "年龄不符合要求（要求：" + trial.getAgeMin() + "-" + trial.getAgeMax() + "岁，实际：" + subject.getAge() + "岁）";
			return new MatchingResult(subject, 0, reason, details);
		}

		// 2. 性别要求检查
		String genderReq = trial.getGenderRequirement();
		if (genderReq != null && !genderReq.equalsIgnoreCase("ANY") && !genderReq.equalsIgnoreCase("不限")) {
			String subjectGender = subject.getGender();
			if (!normalizeGender(subjectGender).equals(normalizeGender(genderReq))) {
				String reason = "性别不符合要求（要求：" + genderReq + "，实际：" + (subjectGender.equals("M") ? "男" : "女") + "）";
				return new MatchingResult(subject, 0, reason, details);
			}
		}

		// 3. 排除条件检查
		if (config.isEnableExclusionRules()) {
			String exclusionReason = getExclusionReason(subject, trial);
			if (exclusionReason != null) {
				return new MatchingResult(subject, 0, "命中排除条件：" + exclusionReason, details);
			}
		}

		// ========== 第二阶段：多维度评分 ==========
		
		// 基础分
		int baseScore = config.getBaseScore();
		details.setBaseScore(baseScore);
		
		// 年龄优先级分数
		int ageScore = calculateAgeScore(subject.getAge(), trial.getAgeMin(), trial.getAgeMax(), config.getAgeWeight());
		details.setAgeScore(ageScore);
		int midAge = (trial.getAgeMin() + trial.getAgeMax()) / 2;
		int ageDiff = Math.abs(subject.getAge() - midAge);
		details.setAgeReason("年龄" + subject.getAge() + "岁，距离理想年龄" + midAge + "岁相差" + ageDiff + "岁");

		// 健康状况匹配分数
		int healthScore = calculateHealthMatchScore(subject.getHealthConditions(), trial.getRequiredConditions(), config.getHealthWeight());
		details.setHealthScore(healthScore);
		List<String> requiredKeywords = TextNormalizer.extractKeywords(trial.getRequiredConditions());
		List<String> matchedKeywords = TextNormalizer.getMatchedKeywords(subject.getHealthConditions(), requiredKeywords);
		if (!matchedKeywords.isEmpty()) {
			details.setHealthReason("匹配关键词：" + String.join("、", matchedKeywords));
		} else {
			details.setHealthReason("未匹配到特定健康要求");
		}

		// 优先条件加分
		int priorityScore = 0;
		StringBuilder priorityReasonBuilder = new StringBuilder();
		if (config.isEnablePriorityRules()) {
			List<MatchingRule> priorityRules = matchingRuleRepository.findByRuleTypeAndIsActiveTrue("PRIORITY");
			String combinedText = (subject.getHealthConditions() != null ? subject.getHealthConditions() : "") + " " + 
			                      (subject.getMedicalHistory() != null ? subject.getMedicalHistory() : "");
			
			for (MatchingRule rule : priorityRules) {
				List<String> keywords = TextNormalizer.extractKeywords(rule.getKeywords());
				List<String> matched = TextNormalizer.getMatchedKeywords(combinedText, keywords);
				if (!matched.isEmpty()) {
					int score = (rule.getWeightScore() != null ? rule.getWeightScore() : 5);
					priorityScore += score;
					if (priorityReasonBuilder.length() > 0) {
						priorityReasonBuilder.append("；");
					}
					priorityReasonBuilder.append(rule.getDescription()).append("(+").append(score).append("分)");
				}
			}
			priorityScore = Math.min(priorityScore, config.getPriorityWeight());
		}
		details.setPriorityScore(priorityScore);
		details.setPriorityReason(priorityReasonBuilder.length() > 0 ? priorityReasonBuilder.toString() : "无优先条件加分");

		// 计算总分
		int totalScore = baseScore + ageScore + healthScore + priorityScore;
		totalScore = Math.min(totalScore, 100);
		
		String explanation = generateMatchingExplanation(subject, trial, totalScore);
		
		return new MatchingResult(subject, totalScore, explanation, details);
	}
	
	/**
	 * 获取排除原因
	 * 
	 * @return 排除原因，如果未被排除则返回null
	 */
	private String getExclusionReason(User subject, ClinicalTrial trial) {
		String subjectHealth = subject.getHealthConditions();
		String subjectMedicalHistory = subject.getMedicalHistory();
		
		String combinedText = (subjectHealth != null ? subjectHealth : "") + " " + 
		                      (subjectMedicalHistory != null ? subjectMedicalHistory : "");
		
		if (combinedText.trim().isEmpty()) {
			return null;
		}

		// 1. 检查试验的排除条件
		if (trial.getExclusionCriteria() != null && !trial.getExclusionCriteria().trim().isEmpty()) {
			List<String> exclusionKeywords = TextNormalizer.extractKeywords(trial.getExclusionCriteria());
			List<String> matched = TextNormalizer.getMatchedKeywords(combinedText, exclusionKeywords);
			if (!matched.isEmpty()) {
				return "试验排除条件 - " + String.join("、", matched);
			}
		}

		// 2. 检查全局排除规则
		List<MatchingRule> exclusionRules = matchingRuleRepository.findByRuleTypeAndIsActiveTrue("EXCLUSION");
		for (MatchingRule rule : exclusionRules) {
			List<String> keywords = TextNormalizer.extractKeywords(rule.getKeywords());
			List<String> matched = TextNormalizer.getMatchedKeywords(combinedText, keywords);
			if (!matched.isEmpty()) {
				return rule.getDescription() + " - " + String.join("、", matched);
			}
		}

		return null;
	}

	/**
	 * 计算受试者与临床试验的匹配分数（带配置）
	 * 简化版本，只返回分数
	 */
	@Override
	public int calculateMatchingScore(User subject, ClinicalTrial trial, MatchingConfig config) {
		MatchingResult result = calculateMatchingScoreWithDetails(subject, trial, config);
		return result.getMatchingScore();
	}

	/**
	 * 检查受试者是否被排除
	 * 扫描受试者健康描述，如果命中排除关键词则直接剔除
	 */
	private boolean isExcluded(User subject, ClinicalTrial trial) {
		return getExclusionReason(subject, trial) != null;
	}

	/**
	 * 计算优先条件分数
	 * 如果受试者符合优先条件，给予额外加分
	 */
	private int calculatePriorityScore(User subject, ClinicalTrial trial, int maxScore) {
		String subjectHealth = subject.getHealthConditions();
		String subjectMedicalHistory = subject.getMedicalHistory();
		
		String combinedText = (subjectHealth != null ? subjectHealth : "") + " " + 
		                      (subjectMedicalHistory != null ? subjectMedicalHistory : "");
		
		if (combinedText.trim().isEmpty()) {
			return 0;
		}

		// 查询优先规则
		List<MatchingRule> priorityRules = matchingRuleRepository.findByRuleTypeAndIsActiveTrue("PRIORITY");
		
		int totalScore = 0;
		for (MatchingRule rule : priorityRules) {
			List<String> keywords = TextNormalizer.extractKeywords(rule.getKeywords());
			if (TextNormalizer.containsAnyKeyword(combinedText, keywords)) {
				// 根据规则的权重分数加分
				totalScore += (rule.getWeightScore() != null ? rule.getWeightScore() : 5);
			}
		}

		// 限制在最大分数范围内
		return Math.min(totalScore, maxScore);
	}

	/**
	 * 计算年龄优先级分数
	 * 年龄越接近年龄范围中值，分数越高
	 */
	private int calculateAgeScore(int age, int minAge, int maxAge, int maxScore) {
		int midAge = (minAge + maxAge) / 2;
		int ageRange = maxAge - minAge;

		// 避免除以0
		if (ageRange == 0) {
			return maxScore;
		}

		int ageDiff = Math.abs(age - midAge);
		double ratio = 1.0 - ((double) ageDiff / ageRange);
		return (int) (maxScore * Math.max(0, ratio));
	}

	/**
	 * 计算健康状况匹配分数（优化版）
	 * 使用文本标准化和近义词匹配算法
	 */
	private int calculateHealthMatchScore(String subjectHealth, String trialRequirement, int maxScore) {
		// 如果试验没有特殊健康要求，给满分
		if (trialRequirement == null || trialRequirement.trim().isEmpty()) {
			return maxScore;
		}

		// 如果受试者没有填写健康状况，给基础分
		if (subjectHealth == null || subjectHealth.trim().isEmpty()) {
			return maxScore / 2;
		}

		// 提取关键词
		List<String> keywords = TextNormalizer.extractKeywords(trialRequirement);
		
		if (keywords.isEmpty()) {
			return maxScore;
		}

		// 计算匹配数量（支持近义词）
		int matchCount = TextNormalizer.countMatchedKeywords(subjectHealth, keywords);

		// 计算匹配比例
		return (int) ((double) maxScore * matchCount / keywords.size());
	}

	/**
	 * 标准化性别值
	 */
	private String normalizeGender(String gender) {
		if (gender == null) {
			return "";
		}
		String g = gender.trim().toUpperCase();
		if (g.equals("MALE") || g.equals("男") || g.equals("M")) {
			return "MALE";
		} else if (g.equals("FEMALE") || g.equals("女") || g.equals("F")) {
			return "FEMALE";
		}
		return g;
	}

	/**
	 * 获取临床试验的匹配受试者列表（按分数排序）
	 */
	@Override
	public List<MatchingResult> getMatchedSubjects(Long trialId) {
		ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElse(null);
		if (trial == null) {
			return new ArrayList<>();
		}

		List<User> subjects = subjectService.getAvailableSubjects();
		return batchCalculateMatching(subjects, trial);
	}
	
	/**
	 * 获取不符合条件的受试者列表
	 */
	@Override
	public List<MatchingResult> getExcludedSubjects(Long trialId) {
		ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElse(null);
		if (trial == null) {
			return new ArrayList<>();
		}

		List<User> subjects = subjectService.getAvailableSubjects();
		MatchingConfig config = new MatchingConfig();
		List<MatchingResult> excludedResults = new ArrayList<>();

		for (User subject : subjects) {
			if (!subject.isStatus()) {
				continue;
			}

			MatchingResult result = calculateMatchingScoreWithDetails(subject, trial, config);
			
			// 只保留分数为0的（不符合条件的）
			if (result.getMatchingScore() == 0) {
				excludedResults.add(result);
			}
		}

		return excludedResults;
	}

	/**
	 * 批量计算匹配分数（使用默认配置）
	 */
	@Override
	public List<MatchingResult> batchCalculateMatching(List<User> subjects, ClinicalTrial trial) {
		return batchCalculateMatching(subjects, trial, new MatchingConfig());
	}

	/**
	 * 批量计算匹配分数（带配置）
	 * 
	 * @param subjects 受试者列表
	 * @param trial    临床试验
	 * @param config   匹配配置
	 * @return 匹配结果列表（按分数降序排序，包含详细评分信息）
	 */
	@Override
	public List<MatchingResult> batchCalculateMatching(List<User> subjects, ClinicalTrial trial, MatchingConfig config) {
		List<MatchingResult> results = new ArrayList<>();

		for (User subject : subjects) {
			// 只处理状态为活跃的受试者
			if (!subject.isStatus()) {
				continue;
			}

			// 计算匹配分数（带详细信息）
			MatchingResult result = calculateMatchingScoreWithDetails(subject, trial, config);

			// 只保留达到推荐阈值的受试者
			if (result.getMatchingScore() >= config.getRecommendThreshold()) {
				results.add(result);
			}
		}

		// 按匹配分数降序排序
		results.sort(Comparator.comparingInt(MatchingResult::getMatchingScore).reversed());

		return results;
	}

	/**
	 * 生成匹配说明（优化版）
	 */
	private String generateMatchingExplanation(User subject, ClinicalTrial trial, int score) {
		if (score == 0) {
			// 检查不匹配的原因
			if (subject.getAge() < trial.getAgeMin() || subject.getAge() > trial.getAgeMax()) {
				return "年龄不符合要求（要求：" + trial.getAgeMin() + "-" + trial.getAgeMax() + "岁）";
			}
			String genderReq = trial.getGenderRequirement();
			if (genderReq != null && !genderReq.equalsIgnoreCase("ANY") && !genderReq.equalsIgnoreCase("不限")) {
				if (!normalizeGender(subject.getGender()).equals(normalizeGender(genderReq))) {
					return "性别不符合要求（要求：" + genderReq + "）";
				}
			}
			if (isExcluded(subject, trial)) {
				return "命中排除条件，不符合入组要求";
			}
			return "不符合基本条件";
		} else if (score >= 90) {
			return "高度匹配，强烈推荐";
		} else if (score >= 75) {
			return "匹配度良好，推荐";
		} else if (score >= 60) {
			return "基本匹配，可考虑";
		} else {
			return "匹配度较低";
		}
	}
}
