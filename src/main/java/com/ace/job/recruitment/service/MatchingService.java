package com.ace.job.recruitment.service;

import java.util.List;

import com.ace.job.recruitment.dto.MatchingConfig;
import com.ace.job.recruitment.dto.MatchingResult;
import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.entity.User;

/**
 * 智能匹配服务接口
 * 核心功能：计算受试者与临床试验的匹配分数
 */
public interface MatchingService {

	/**
	 * 计算受试者与临床试验的匹配分数
	 * 
	 * @param subject 受试者（User对象，role=PARTICIPANT）
	 * @param trial   临床试验
	 * @return 匹配分数 (0-100)
	 */
	int calculateMatchingScore(User subject, ClinicalTrial trial);

	/**
	 * 计算受试者与临床试验的匹配分数（带配置）
	 * 
	 * @param subject 受试者
	 * @param trial   临床试验
	 * @param config  匹配配置
	 * @return 匹配分数 (0-100)
	 */
	int calculateMatchingScore(User subject, ClinicalTrial trial, MatchingConfig config);

	/**
	 * 获取临床试验的匹配受试者列表（按分数排序）
	 * 
	 * @param trialId 临床试验ID
	 * @return 匹配结果列表
	 */
	List<MatchingResult> getMatchedSubjects(Long trialId);
	
	/**
	 * 获取不符合条件的受试者列表
	 * 
	 * @param trialId 临床试验ID
	 * @return 不符合条件的受试者列表（分数为0）
	 */
	List<MatchingResult> getExcludedSubjects(Long trialId);

	/**
	 * 批量计算匹配分数
	 * 
	 * @param subjects 受试者列表
	 * @param trial    临床试验
	 * @return 匹配结果列表
	 */
	List<MatchingResult> batchCalculateMatching(List<User> subjects, ClinicalTrial trial);
	
	/**
	 * 批量计算匹配分数（带配置）
	 * 
	 * @param subjects 受试者列表
	 * @param trial    临床试验
	 * @param config   匹配配置
	 * @return 匹配结果列表
	 */
	List<MatchingResult> batchCalculateMatching(List<User> subjects, ClinicalTrial trial, MatchingConfig config);
}
