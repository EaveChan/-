package com.ace.job.recruitment.service;

import com.ace.job.recruitment.dto.SystemStatistics;

/**
 * 系统管理员统计服务接口
 */
public interface AdminStatisticsService {
	
	/**
	 * 获取系统统计数据
	 */
	SystemStatistics getSystemStatistics();
	
	/**
	 * 获取用户总数
	 */
	long getTotalUsers();
	
	/**
	 * 根据角色获取用户数量
	 */
	long getUserCountByRole(String role);
	
	/**
	 * 获取临床试验总数
	 */
	long getTotalTrials();
}
