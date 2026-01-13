package com.ace.job.recruitment.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ace.job.recruitment.dto.DashboardCountDTO;
import com.ace.job.recruitment.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Override
	public int getMinYear() {
		// 返回当前年份作为最小年份
		return LocalDate.now().getYear();
	}

	@Override
	public DashboardCountDTO getDashboardCounts(int year) {
		// 简化实现，返回空的DTO
		// 临床试验系统不需要按年份统计
		return new DashboardCountDTO();
	}

	@Override
	public DashboardCountDTO getDashboardCountsForDepartment(int year, int department) {
		// 简化实现，返回空的DTO
		// 临床试验系统不需要部门统计
		return new DashboardCountDTO();
	}

}