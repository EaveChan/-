package com.ace.job.recruitment.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ace.job.recruitment.dto.RolePermission;
import com.ace.job.recruitment.dto.SystemStatistics;
import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.repository.UserRepository;
import com.ace.job.recruitment.service.AdminStatisticsService;

/**
 * 管理员控制器
 * 处理用户管理等管理员功能
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('Admin','Default-Admin')")
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminStatisticsService adminStatisticsService;

	/**
	 * 系统概览页面
	 */
	@GetMapping("/overview")
	public String showOverview(Model model) {
		SystemStatistics stats = adminStatisticsService.getSystemStatistics();
		model.addAttribute("stats", stats);
		return "admin/overview";
	}

	/**
	 * 角色权限管理页面
	 */
	@GetMapping("/roles")
	public String showRoles(Model model) {
		List<RolePermission> rolePermissions = new ArrayList<>();
		
		// ADMIN 角色
		rolePermissions.add(new RolePermission(
			"Admin",
			"系统管理员",
			Arrays.asList("系统概览", "用户管理", "角色权限管理")
		));
		
		// RESEARCH_ASSISTANT 角色
		rolePermissions.add(new RolePermission(
			"RESEARCH_ASSISTANT",
			"研究助理",
			Arrays.asList("临床试验管理", "受试者管理", "智能匹配", "入组管理")
		));
		
		// PARTICIPANT 角色
		rolePermissions.add(new RolePermission(
			"PARTICIPANT",
			"受试者",
			Arrays.asList("查看临床试验", "报名试验", "查看个人信息", "查看报名状态")
		));
		
		model.addAttribute("rolePermissions", rolePermissions);
		return "admin/roles";
	}

	/**
	 * 显示用户列表
	 */
	@GetMapping("/user-management/list")
	public String listUsers(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "admin/users";
	}

	/**
	 * 显示新建用户表单
	 */
	@GetMapping("/user-management/add")
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("isEdit", false);
		return "admin/user-form";
	}

	/**
	 * 创建用户
	 */
	@PostMapping("/user-management/add")
	public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		try {
			// 检查用户名是否已存在
			User existingUser = userRepository.findByEmail(user.getEmail());
			if (existingUser != null) {
				redirectAttributes.addFlashAttribute("error", "邮箱已存在");
				return "redirect:/admin/user-management/add";
			}

			// 加密密码
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			// 设置默认状态为活跃
			user.setStatus(true);

			userRepository.save(user);
			redirectAttributes.addFlashAttribute("success", "用户创建成功");
			return "redirect:/admin/user-management/list";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "创建失败：" + e.getMessage());
			return "redirect:/admin/user-management/add";
		}
	}

	/**
	 * 显示编辑用户表单
	 */
	@GetMapping("/user-management/edit/{id}")
	public String showEditUserForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			redirectAttributes.addFlashAttribute("error", "用户不存在");
			return "redirect:/admin/user-management/list";
		}
		model.addAttribute("user", user);
		model.addAttribute("isEdit", true);
		return "admin/user-form";
	}

	/**
	 * 更新用户
	 */
	@PostMapping("/user-management/edit/{id}")
	public String updateUser(@PathVariable int id, @ModelAttribute User user,
			RedirectAttributes redirectAttributes) {
		try {
			User existingUser = userRepository.findById(id).orElse(null);
			if (existingUser == null) {
				redirectAttributes.addFlashAttribute("error", "用户不存在");
				return "redirect:/admin/user-management/list";
			}

			// 更新字段
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setRole(user.getRole());

			// 如果提供了新密码，则更新密码
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			userRepository.save(existingUser);
			redirectAttributes.addFlashAttribute("success", "用户更新成功");
			return "redirect:/admin/user-management/list";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
			return "redirect:/admin/user-management/edit/" + id;
		}
	}

	/**
	 * 激活用户
	 */
	@PostMapping("/user-management/activate/{id}")
	public String activateUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			User user = userRepository.findById(id).orElse(null);
			if (user != null) {
				user.setStatus(true);
				userRepository.save(user);
				redirectAttributes.addFlashAttribute("success", "用户已激活");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "激活失败：" + e.getMessage());
		}
		return "redirect:/admin/user-management/list";
	}

	/**
	 * 禁用用户
	 */
	@PostMapping("/user-management/deactivate/{id}")
	public String deactivateUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			User user = userRepository.findById(id).orElse(null);
			if (user != null) {
				user.setStatus(false);
				userRepository.save(user);
				redirectAttributes.addFlashAttribute("success", "用户已禁用");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "禁用失败：" + e.getMessage());
		}
		return "redirect:/admin/user-management/list";
	}
}
