package com.ace.job.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ace.job.recruitment.dto.MatchingResult;
import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.service.ClinicalTrialService;
import com.ace.job.recruitment.service.MatchingService;

/**
 * 智能匹配控制器
 * 处理临床试验与受试者的智能匹配功能
 */
@Controller
@RequestMapping("/matching")
public class MatchingController {

	@Autowired
	private MatchingService matchingService;

	@Autowired
	private ClinicalTrialService clinicalTrialService;

	/**
	 * 显示智能匹配主页面
	 */
	@GetMapping
	public String matchingIndex(Model model) {
		// 获取所有招募中的临床试验
		List<ClinicalTrial> activeTrials = clinicalTrialService.getActiveTrials();
		
		// 截断描述文本，避免表格过宽
		for (ClinicalTrial trial : activeTrials) {
			if (trial.getTrialDescription() != null && trial.getTrialDescription().length() > 35) {
				trial.setTrialDescription(trial.getTrialDescription().substring(0, 35) + "...");
			}
		}
		
		model.addAttribute("trials", activeTrials);
		model.addAttribute("clinicalTrialService", clinicalTrialService);
		return "matching/index";
	}

	/**
	 * 显示指定临床试验的匹配结果
	 */
	@GetMapping("/trial/{trialId}")
	public String showMatchingResults(@PathVariable Long trialId, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			// 获取临床试验信息
			ClinicalTrial trial = clinicalTrialService.getTrialById(trialId);
			if (trial == null) {
				redirectAttributes.addFlashAttribute("error", "临床试验不存在");
				return "redirect:/matching";
			}

			// 获取匹配结果
			List<MatchingResult> matchingResults = matchingService.getMatchedSubjects(trialId);

			model.addAttribute("trial", trial);
			model.addAttribute("matchingResults", matchingResults);
			model.addAttribute("clinicalTrialService", clinicalTrialService);
			return "matching/results";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "获取匹配结果失败：" + e.getMessage());
			return "redirect:/matching";
		}
	}
	
	/**
	 * 显示不符合条件的受试者列表
	 */
	@GetMapping("/trial/{trialId}/excluded")
	public String showExcludedSubjects(@PathVariable Long trialId, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			// 获取临床试验信息
			ClinicalTrial trial = clinicalTrialService.getTrialById(trialId);
			if (trial == null) {
				redirectAttributes.addFlashAttribute("error", "临床试验不存在");
				return "redirect:/matching";
			}

			// 获取不符合条件的受试者
			List<MatchingResult> excludedResults = matchingService.getExcludedSubjects(trialId);

			model.addAttribute("trial", trial);
			model.addAttribute("excludedResults", excludedResults);
			model.addAttribute("clinicalTrialService", clinicalTrialService);
			return "matching/excluded";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "获取排除列表失败：" + e.getMessage());
			return "redirect:/matching";
		}
	}
}
