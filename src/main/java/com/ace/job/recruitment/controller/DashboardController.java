package com.ace.job.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.job.recruitment.dto.DashboardCountDTO;
import com.ace.job.recruitment.service.ClinicalTrialService;
import com.ace.job.recruitment.service.DashboardService;
import com.ace.job.recruitment.service.EnrollmentService;
import com.ace.job.recruitment.service.SubjectService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private ClinicalTrialService clinicalTrialService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private EnrollmentService enrollmentService;

	@GetMapping
	public String showDashboardHR(Model model) {
		int minYear = dashboardService.getMinYear();

		// 添加临床试验统计数据
		int totalTrials = clinicalTrialService.getAllTrials().size();
		int activeTrials = clinicalTrialService.getActiveTrials().size();
		int totalSubjects = subjectService.getAllSubjects().size();
		long pendingEnrollments = enrollmentService.getPendingApplications().size();
		long totalEnrollments = enrollmentService.getAllApplications().stream()
				.filter(app -> "APPROVED".equals(app.getStatus()) || "ENROLLED".equals(app.getStatus())).count();

		model.addAttribute("minYear", minYear);
		model.addAttribute("totalTrials", totalTrials);
		model.addAttribute("activeTrials", activeTrials);
		model.addAttribute("totalSubjects", totalSubjects);
		model.addAttribute("pendingEnrollments", pendingEnrollments);
		model.addAttribute("totalEnrollments", totalEnrollments);

		return "dashboard/dashboard";
	}

	@GetMapping("/counts")
	@ResponseBody
	public ResponseEntity<DashboardCountDTO> getDashboardCounts(@RequestParam int year) {
		DashboardCountDTO dashboardCountDTO = dashboardService.getDashboardCounts(year);
		return new ResponseEntity<>(dashboardCountDTO, HttpStatus.OK);
	}
}
