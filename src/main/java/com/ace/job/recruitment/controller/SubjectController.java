package com.ace.job.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.service.SubjectService;

/**
 * 受试者控制器
 * 处理受试者的管理操作
 */
@Controller
@RequestMapping("/subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	/**
	 * 显示受试者列表页面
	 */
	@GetMapping
	public String listSubjects(@RequestParam(required = false) String status, Model model) {
		List<User> subjects;
		if (status != null && !status.isEmpty()) {
			subjects = subjectService.getSubjectsByStatus(status);
		} else {
			subjects = subjectService.getAllSubjects();
		}
		model.addAttribute("subjects", subjects);
		model.addAttribute("status", status);
		return "subjects/list";
	}

	/**
	 * 搜索受试者
	 */
	@GetMapping("/search")
	public String searchSubjects(@RequestParam(required = false) String keyword, Model model) {
		List<User> subjects = subjectService.searchSubjects(keyword);
		model.addAttribute("subjects", subjects);
		model.addAttribute("keyword", keyword);
		return "subjects/list";
	}

	/**
	 * 显示新建受试者表单
	 */
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("subject", new User());
		return "subjects/add";
	}

	/**
	 * 创建受试者
	 */
	@PostMapping("/add")
	public String createSubject(@ModelAttribute User subject, RedirectAttributes redirectAttributes) {
		try {
			subjectService.createSubject(subject);
			redirectAttributes.addFlashAttribute("success", "受试者创建成功");
			return "redirect:/subjects";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "创建失败：" + e.getMessage());
			return "redirect:/subjects/add";
		}
	}

	/**
	 * 显示编辑受试者表单
	 */
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
		User subject = subjectService.getSubjectById(id);
		if (subject == null) {
			redirectAttributes.addFlashAttribute("error", "受试者不存在");
			return "redirect:/subjects";
		}
		model.addAttribute("subject", subject);
		return "subjects/edit";
	}

	/**
	 * 更新受试者
	 */
	@PostMapping("/edit/{id}")
	public String updateSubject(@PathVariable int id, @ModelAttribute User subject,
			RedirectAttributes redirectAttributes) {
		try {
			User updated = subjectService.updateSubject(id, subject);
			if (updated == null) {
				redirectAttributes.addFlashAttribute("error", "受试者不存在");
				return "redirect:/subjects";
			}

			redirectAttributes.addFlashAttribute("success", "受试者信息更新成功");
			return "redirect:/subjects/view/" + id;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
			return "redirect:/subjects/edit/" + id;
		}
	}

	/**
	 * 显示受试者详情页面
	 */
	@GetMapping("/view/{id}")
	public String viewSubject(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
		User subject = subjectService.getSubjectById(id);
		if (subject == null) {
			redirectAttributes.addFlashAttribute("error", "受试者不存在");
			return "redirect:/subjects";
		}

		model.addAttribute("subject", subject);
		return "subjects/view";
	}
}
