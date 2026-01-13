package com.ace.job.recruitment.dto;

/**
 * 系统统计数据 DTO
 * 用于系统概览页面展示统计信息
 */
public class SystemStatistics {
	private long totalUsers;
	private long adminCount;
	private long researchAssistantCount;
	private long participantCount;
	private long totalTrials;
	private String systemStatus;

	public SystemStatistics() {
		this.systemStatus = "正常运行";
	}

	public long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public long getAdminCount() {
		return adminCount;
	}

	public void setAdminCount(long adminCount) {
		this.adminCount = adminCount;
	}

	public long getResearchAssistantCount() {
		return researchAssistantCount;
	}

	public void setResearchAssistantCount(long researchAssistantCount) {
		this.researchAssistantCount = researchAssistantCount;
	}

	public long getParticipantCount() {
		return participantCount;
	}

	public void setParticipantCount(long participantCount) {
		this.participantCount = participantCount;
	}

	public long getTotalTrials() {
		return totalTrials;
	}

	public void setTotalTrials(long totalTrials) {
		this.totalTrials = totalTrials;
	}

	public String getSystemStatus() {
		return systemStatus;
	}

	public void setSystemStatus(String systemStatus) {
		this.systemStatus = systemStatus;
	}
}
