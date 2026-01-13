package com.ace.job.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.model.AppUserDetails;
import com.ace.job.recruitment.service.ClinicalTrialService;

/**
 * 临床试验控制器
 * 处理临床试验的CRUD操作
 */
@Controller
@RequestMapping("/trials")
public class ClinicalTrialController {

	@Autowired
	private ClinicalTrialService clinicalTrialService;

	/**
	 * 显示临床试验列表页面
	 */
	@GetMapping
	public String listTrials(@RequestParam(required = false) String status, Model model) {
		List<ClinicalTrial> trials;
		if (status != null && !status.isEmpty()) {
			trials = clinicalTrialService.getTrialsByStatus(status);
		} else {
			trials = clinicalTrialService.getAllTrials();
		}
		model.addAttribute("trials", trials);
		model.addAttribute("status", status);
		model.addAttribute("clinicalTrialService", clinicalTrialService);
		return "trials/list";
	}

	/**
	 * 显示新建临床试验表单
	 */
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("trial", new ClinicalTrial());
		model.addAttribute("isEdit", false);
		return "trials/add";
	}

	/**
	 * 创建临床试验
	 */
	@PostMapping("/add")
	public String createTrial(@ModelAttribute ClinicalTrial trial, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		try {
			// 获取当前用户ID
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			trial.setCreatedUserId(userDetails.getUser().getId());

			// 验证年龄范围
			if (trial.getAgeMin() > trial.getAgeMax()) {
				redirectAttributes.addFlashAttribute("error", "最小年龄不能大于最大年龄");
				return "redirect:/trials/add";
			}

			clinicalTrialService.createTrial(trial);
			redirectAttributes.addFlashAttribute("success", "临床试验创建成功");
			return "redirect:/trials";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "创建失败：" + e.getMessage());
			return "redirect:/trials/add";
		}
	}

	/**
	 * 显示编辑临床试验表单
	 */
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		ClinicalTrial trial = clinicalTrialService.getTrialById(id);
		if (trial == null) {
			redirectAttributes.addFlashAttribute("error", "临床试验不存在");
			return "redirect:/trials";
		}
		model.addAttribute("trial", trial);
		model.addAttribute("isEdit", true);
		return "trials/edit";
	}

	/**
	 * 更新临床试验
	 */
	@PostMapping("/edit/{id}")
	public String updateTrial(@PathVariable Long id, @ModelAttribute ClinicalTrial trial,
			Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			// 获取当前用户ID
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			trial.setUpdatedUserId(userDetails.getUser().getId());

			// 验证年龄范围
			if (trial.getAgeMin() > trial.getAgeMax()) {
				redirectAttributes.addFlashAttribute("error", "最小年龄不能大于最大年龄");
				return "redirect:/trials/edit/" + id;
			}

			ClinicalTrial updated = clinicalTrialService.updateTrial(id, trial);
			if (updated == null) {
				redirectAttributes.addFlashAttribute("error", "临床试验不存在");
				return "redirect:/trials";
			}

			redirectAttributes.addFlashAttribute("success", "临床试验更新成功");
			return "redirect:/trials/view/" + id;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
			return "redirect:/trials/edit/" + id;
		}
	}

	/**
	 * 显示临床试验详情页面
	 */
	@GetMapping("/view/{id}")
	public String viewTrial(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		ClinicalTrial trial = clinicalTrialService.getTrialById(id);
		if (trial == null) {
			redirectAttributes.addFlashAttribute("error", "临床试验不存在");
			return "redirect:/trials";
		}

		// 获取已入组人数
		int enrolledCount = clinicalTrialService.getEnrolledCount(id);

		model.addAttribute("trial", trial);
		model.addAttribute("enrolledCount", enrolledCount);
		return "trials/view";
	}

	/**
	 * 关闭临床试验
	 */
	@PostMapping("/close/{id}")
	public String closeTrial(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			clinicalTrialService.closeTrial(id);
			redirectAttributes.addFlashAttribute("success", "临床试验已关闭");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "关闭失败：" + e.getMessage());
		}
		return "redirect:/trials";
	}

	/**
	 * 删除临床试验
	 */
	@PostMapping("/delete/{id}")
	public String deleteTrial(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			ClinicalTrial trial = clinicalTrialService.getTrialById(id);
			if (trial == null) {
				redirectAttributes.addFlashAttribute("error", "临床试验不存在");
				return "redirect:/trials";
			}
			clinicalTrialService.deleteTrial(id);
			redirectAttributes.addFlashAttribute("success", "临床试验已删除");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
		}
		return "redirect:/trials";
	}
}
