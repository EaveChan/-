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

import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.entity.EnrollmentApplication;
import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.model.AppUserDetails;
import com.ace.job.recruitment.service.ClinicalTrialService;
import com.ace.job.recruitment.service.EnrollmentService;
import com.ace.job.recruitment.service.MatchingService;
import com.ace.job.recruitment.service.UserService;

/**
 * 受试者端控制器
 * 处理受试者的临床试验报名、查看报名状态等功能
 */
@Controller
@RequestMapping("/participant")
public class ParticipantController {

	@Autowired
	private ClinicalTrialService clinicalTrialService;

	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private MatchingService matchingService;

	/**
	 * 受试者首页
	 */
	@GetMapping
	public String participantHome(Model model, Authentication authentication) {
		// 获取当前用户
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();

		// 统计信息
		long activeTrialsCount = clinicalTrialService.getActiveTrials().size();
		List<EnrollmentApplication> myApplications = enrollmentService.getApplicationsByUserId(user.getId());

		model.addAttribute("activeTrialsCount", activeTrialsCount);
		model.addAttribute("myApplicationsCount", myApplications.size());
		model.addAttribute("user", user);

		return "participant/home";
	}

	/**
	 * 临床试验列表（可报名）
	 */
	@GetMapping("/trials")
	public String listTrials(Model model, Authentication authentication) {
		// 获取所有招募中的临床试验
		List<ClinicalTrial> activeTrials = clinicalTrialService.getActiveTrials();

		// 获取当前用户已报名的试验ID列表
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		List<EnrollmentApplication> myApplications = enrollmentService.getApplicationsByUserId(user.getId());

		// 为每个试验计算匹配分数
		java.util.Map<Long, Integer> matchScores = new java.util.HashMap<>();
		for (ClinicalTrial trial : activeTrials) {
			int score = matchingService.calculateMatchingScore(user, trial);
			matchScores.put(trial.getId(), score);
		}

		model.addAttribute("trials", activeTrials);
		model.addAttribute("myApplications", myApplications);
		model.addAttribute("matchScores", matchScores);

		return "participant/trials";
	}

	/**
	 * 临床试验详情页
	 */
	@GetMapping("/trials/{id}")
	public String viewTrial(@PathVariable Long id, Model model, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		ClinicalTrial trial = clinicalTrialService.getTrialById(id);
		if (trial == null) {
			redirectAttributes.addFlashAttribute("error", "临床试验不存在");
			return "redirect:/participant/trials";
		}

		// 检查是否已报名
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		EnrollmentApplication existingApplication = enrollmentService.getApplicationByUserAndTrial(user.getId(), id);

		model.addAttribute("trial", trial);
		model.addAttribute("existingApplication", existingApplication);
		model.addAttribute("user", user);

		return "participant/trial-detail";
	}

	/**
	 * 提交报名申请
	 */
	@PostMapping("/trials/{id}/apply")
	public String applyForTrial(@PathVariable Long id, @RequestParam String healthConditions,
			@RequestParam(required = false) String medicalHistory, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		try {
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			User user = userDetails.getUser();

			// 检查是否已报名
			EnrollmentApplication existingApplication = enrollmentService.getApplicationByUserAndTrial(user.getId(),
					id);
			if (existingApplication != null) {
				redirectAttributes.addFlashAttribute("error", "您已经报名过此临床试验");
				return "redirect:/participant/trials/" + id;
			}

			// 更新用户健康信息
			user.setHealthConditions(healthConditions);
			if (medicalHistory != null && !medicalHistory.isEmpty()) {
				user.setMedicalHistory(medicalHistory);
			}
			userService.updateUser(user);

			// 创建报名申请
			enrollmentService.createApplication(user.getId(), id);

			redirectAttributes.addFlashAttribute("success", "报名成功！我们将尽快处理您的申请");
			return "redirect:/participant/my-applications";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "报名失败：" + e.getMessage());
			return "redirect:/participant/trials/" + id;
		}
	}

	/**
	 * 我的报名列表
	 */
	@GetMapping("/my-applications")
	public String myApplications(Model model, Authentication authentication) {
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();

		List<EnrollmentApplication> applications = enrollmentService.getApplicationsByUserId(user.getId());

		model.addAttribute("applications", applications);
		model.addAttribute("user", user);

		return "participant/my-applications";
	}

	/**
	 * 个人信息页面
	 */
	@GetMapping("/profile")
	public String profile(Model model, Authentication authentication) {
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();

		model.addAttribute("user", user);

		return "participant/profile";
	}

	/**
	 * 更新个人信息
	 */
	@PostMapping("/profile/update")
	public String updateProfile(@RequestParam Integer age, @RequestParam String gender,
			@RequestParam String healthConditions, @RequestParam(required = false) String medicalHistory,
			Authentication authentication, RedirectAttributes redirectAttributes) {
		try {
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			User user = userDetails.getUser();

			user.setAge(age);
			user.setGender(gender);
			user.setHealthConditions(healthConditions);
			user.setMedicalHistory(medicalHistory);

			userService.updateUser(user);

			redirectAttributes.addFlashAttribute("success", "个人信息更新成功");
			return "redirect:/participant/profile";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
			return "redirect:/participant/profile";
		}
	}

	/**
	 * 撤销报名
	 */
	@PostMapping("/applications/{id}/cancel")
	public String cancelApplication(@PathVariable Long id, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		try {
			AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
			User user = userDetails.getUser();

			EnrollmentApplication application = enrollmentService.getApplicationById(id);
			if (application == null) {
				redirectAttributes.addFlashAttribute("error", "报名记录不存在");
				return "redirect:/participant/my-applications";
			}

			// 检查是否是当前用户的申请
			if (application.getParticipant().getId() != user.getId()) {
				redirectAttributes.addFlashAttribute("error", "无权操作此报名记录");
				return "redirect:/participant/my-applications";
			}

			// 只有待审核状态才能撤销
			if (!"PENDING".equals(application.getStatus())) {
				redirectAttributes.addFlashAttribute("error", "只有待审核状态的报名才能撤销");
				return "redirect:/participant/my-applications";
			}

			// 删除申请
			enrollmentService.deleteApplication(id);

			redirectAttributes.addFlashAttribute("success", "报名已成功撤销");
			return "redirect:/participant/my-applications";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "撤销失败：" + e.getMessage());
			return "redirect:/participant/my-applications";
		}
	}
}
