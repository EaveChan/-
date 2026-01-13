package com.ace.job.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.job.recruitment.dto.SystemStatistics;
import com.ace.job.recruitment.repository.ClinicalTrialRepository;
import com.ace.job.recruitment.repository.UserRepository;
import com.ace.job.recruitment.service.AdminStatisticsService;

/**
 * 系统管理员统计服务实现
 */
@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClinicalTrialRepository clinicalTrialRepository;

	@Override
	public SystemStatistics getSystemStatistics() {
		SystemStatistics stats = new SystemStatistics();
		
		// 统计用户数据
		stats.setTotalUsers(getTotalUsers());
		stats.setAdminCount(getUserCountByRole("Admin") + getUserCountByRole("Default-Admin"));
		stats.setResearchAssistantCount(getUserCountByRole("RESEARCH_ASSISTANT"));
		stats.setParticipantCount(getUserCountByRole("PARTICIPANT"));
		
		// 统计临床试验数据
		stats.setTotalTrials(getTotalTrials());
		
		return stats;
	}

	@Override
	public long getTotalUsers() {
		return userRepository.count();
	}

	@Override
	public long getUserCountByRole(String role) {
		return userRepository.countByRole(role);
	}

	@Override
	public long getTotalTrials() {
		return clinicalTrialRepository.count();
	}
}
