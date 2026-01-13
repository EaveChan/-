package com.ace.job.recruitment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.repository.UserRepository;
import com.ace.job.recruitment.service.SubjectService;

/**
 * 受试者服务实现类
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllSubjects() {
		// 获取所有角色为PARTICIPANT的用户
		return userRepository.findAll().stream().filter(user -> "PARTICIPANT".equals(user.getRole()))
				.collect(Collectors.toList());
	}

	@Override
	public User getSubjectById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User createSubject(User subject) {
		// 设置角色为PARTICIPANT
		subject.setRole("PARTICIPANT");

		// 设置默认状态为活跃
		subject.setStatus(true);

		// 加密密码
		if (subject.getPassword() != null && !subject.getPassword().isEmpty()) {
			subject.setPassword(passwordEncoder.encode(subject.getPassword()));
		}

		return userRepository.save(subject);
	}

	@Override
	public User updateSubject(int id, User subject) {
		User existingSubject = userRepository.findById(id).orElse(null);
		if (existingSubject == null) {
			return null;
		}

		// 更新字段
		existingSubject.setName(subject.getName());
		existingSubject.setEmail(subject.getEmail());
		existingSubject.setAge(subject.getAge());
		existingSubject.setGender(subject.getGender());
		existingSubject.setHealthConditions(subject.getHealthConditions());
		existingSubject.setMedicalHistory(subject.getMedicalHistory());

		// 如果提供了新密码，则更新密码
		if (subject.getPassword() != null && !subject.getPassword().isEmpty()) {
			existingSubject.setPassword(passwordEncoder.encode(subject.getPassword()));
		}

		return userRepository.save(existingSubject);
	}

	@Override
	public List<User> getSubjectsByStatus(boolean status) {
		return userRepository.findAll().stream()
				.filter(user -> "PARTICIPANT".equals(user.getRole()) && user.isStatus() == status)
				.collect(Collectors.toList());
	}

	@Override
	public List<User> searchSubjects(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return getAllSubjects();
		}

		String lowerKeyword = keyword.toLowerCase();
		return userRepository.findAll().stream().filter(user -> "PARTICIPANT".equals(user.getRole())
				&& (user.getName().toLowerCase().contains(lowerKeyword)
						|| user.getEmail().toLowerCase().contains(lowerKeyword)
						|| (user.getHealthConditions() != null
								&& user.getHealthConditions().toLowerCase().contains(lowerKeyword))))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getAvailableSubjects() {
		return userRepository.findAll().stream()
				.filter(user -> "PARTICIPANT".equals(user.getRole()) && user.isStatus())
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getSubjectsByStatus(String status) {
		// 根据状态字符串筛选受试者
		// 这里简化处理，实际应该根据入组状态等更复杂的逻辑
		if ("ACTIVE".equalsIgnoreCase(status)) {
			return getSubjectsByStatus(true);
		} else if ("INACTIVE".equalsIgnoreCase(status)) {
			return getSubjectsByStatus(false);
		} else {
			// 对于其他状态（ENROLLED, REJECTED, PENDING），返回所有受试者
			// 实际应该根据EnrollmentApplication表的状态来筛选
			return getAllSubjects();
		}
	}
}
