package com.ace.job.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ace.job.recruitment.entity.EnrollmentApplication;
import com.ace.job.recruitment.model.AppUserDetails;
import com.ace.job.recruitment.service.EnrollmentService;

/**
 * 入组管理控制器
 * 处理入组申请的管理操作
 */
@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;

	/**
	 * 显示入组申请列表
	 */
	@GetMapping("/enrollments")
	public String listEnrollments(Model model, Authentication authentication) {
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		String role = userDetails.getUser().getRole();

		List<EnrollmentApplication> applications;

		// 根据角色显示不同的申请列表
		if ("PARTICIPANT".equals(role)) {
			// 受试者只能看到自己的申请
			applications = enrollmentService.getApplicationsBySubject(userDetails.getUser().getId());
		} else {
			// 管理员和研究助理可以看到所有申请
			applications = enrollmentService.getAllApplications();
		}

		model.addAttribute("applications", applications);
		return "enrollments/list";
	}

	/**
	 * 创建入组申请
	 */
	@PostMapping("/apply")
	public String applyEnrollment(@RequestParam int subjectId, @RequestParam Long trialId,
			RedirectAttributes redirectAttributes) {
		try {
			enrollmentService.createApplication(subjectId, trialId);
			redirectAttributes.addFlashAttribute("success", "入组申请提交成功");
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "申请失败：" + e.getMessage());
		}
		return "redirect:/enroll/enrollments";
	}

	/**
	 * 批准入组申请
	 */
	@PostMapping("/approve/{id}")
	public String approveEnrollment(@PathVariable Long id, @RequestParam(required = false) String notes,
			Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			enrollmentService.approveEnrollment(id, userDetails.getUser(), notes);
			redirectAttributes.addFlashAttribute("success", "入组申请已批准");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "批准失败：" + e.getMessage());
		}
		return "redirect:/enroll/enrollments";
	}

	/**
	 * 拒绝入组申请
	 */
	@PostMapping("/reject/{id}")
	public String rejectEnrollment(@PathVariable Long id, @RequestParam(required = false) String notes,
			Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			enrollmentService.rejectEnrollment(id, userDetails.getUser(), notes);
			redirectAttributes.addFlashAttribute("success", "入组申请已拒绝");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "拒绝失败：" + e.getMessage());
		}
		return "redirect:/enroll/enrollments";
	}

	/**
	 * 更新申请状态
	 */
	@PostMapping("/enrollments/{id}/status")
	public String updateStatus(@PathVariable Long id, @RequestParam String status,
			RedirectAttributes redirectAttributes) {
		try {
			enrollmentService.updateApplicationStatus(id, status);
			redirectAttributes.addFlashAttribute("success", "状态更新成功");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
		}
		return "redirect:/enroll/enrollments";
	}

	/**
	 * 拒绝受试者（从匹配结果页面）
	 */
	@PostMapping("/reject-subject")
	public String rejectSubject(@RequestParam int subjectId, @RequestParam Long trialId,
			RedirectAttributes redirectAttributes) {
		try {
			enrollmentService.rejectSubjectForTrial(subjectId, trialId);
			redirectAttributes.addFlashAttribute("success", "受试者已被拒绝");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "操作失败：" + e.getMessage());
		}
		return "redirect:/matching/trial/" + trialId;
	}

	/**
	 * 标记为人工复核
	 */
	@PostMapping("/mark-review")
	public String markForReview(@RequestParam int subjectId, @RequestParam Long trialId,
			RedirectAttributes redirectAttributes) {
		try {
			enrollmentService.markForManualReview(subjectId, trialId);
			redirectAttributes.addFlashAttribute("success", "已标记为人工复核");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "操作失败：" + e.getMessage());
		}
		return "redirect:/matching/trial/" + trialId;
	}
}
