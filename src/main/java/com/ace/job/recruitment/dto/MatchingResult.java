package com.ace.job.recruitment.dto;

import com.ace.job.recruitment.entity.User;

/**
 * 匹配结果DTO
 * 用于封装受试者与临床试验的匹配信息
 */
public class MatchingResult {
	private User subject;
	private int matchingScore;
	private String matchingExplanation;
	
	// 评分细则
	private ScoreDetails scoreDetails;

	public MatchingResult() {
	}

	public MatchingResult(User subject, int matchingScore) {
		this.subject = subject;
		this.matchingScore = matchingScore;
	}

	public MatchingResult(User subject, int matchingScore, String matchingExplanation) {
		this.subject = subject;
		this.matchingScore = matchingScore;
		this.matchingExplanation = matchingExplanation;
	}
	
	public MatchingResult(User subject, int matchingScore, String matchingExplanation, ScoreDetails scoreDetails) {
		this.subject = subject;
		this.matchingScore = matchingScore;
		this.matchingExplanation = matchingExplanation;
		this.scoreDetails = scoreDetails;
	}

	public User getSubject() {
		return subject;
	}

	public void setSubject(User subject) {
		this.subject = subject;
	}

	public int getMatchingScore() {
		return matchingScore;
	}

	public void setMatchingScore(int matchingScore) {
		this.matchingScore = matchingScore;
	}

	public String getMatchingExplanation() {
		return matchingExplanation;
	}

	public void setMatchingExplanation(String matchingExplanation) {
		this.matchingExplanation = matchingExplanation;
	}
	
	public ScoreDetails getScoreDetails() {
		return scoreDetails;
	}
	
	public void setScoreDetails(ScoreDetails scoreDetails) {
		this.scoreDetails = scoreDetails;
	}
	
	/**
	 * 评分细则内部类
	 */
	public static class ScoreDetails {
		private int baseScore;           // 基础分
		private int ageScore;            // 年龄分
		private int healthScore;         // 健康状况分
		private int priorityScore;       // 优先条件分
		private String ageReason;        // 年龄评分原因
		private String healthReason;     // 健康评分原因
		private String priorityReason;   // 优先条件原因
		
		public ScoreDetails() {
		}
		
		public ScoreDetails(int baseScore, int ageScore, int healthScore, int priorityScore) {
			this.baseScore = baseScore;
			this.ageScore = ageScore;
			this.healthScore = healthScore;
			this.priorityScore = priorityScore;
		}
		
		// Getters and Setters
		public int getBaseScore() {
			return baseScore;
		}
		
		public void setBaseScore(int baseScore) {
			this.baseScore = baseScore;
		}
		
		public int getAgeScore() {
			return ageScore;
		}
		
		public void setAgeScore(int ageScore) {
			this.ageScore = ageScore;
		}
		
		public int getHealthScore() {
			return healthScore;
		}
		
		public void setHealthScore(int healthScore) {
			this.healthScore = healthScore;
		}
		
		public int getPriorityScore() {
			return priorityScore;
		}
		
		public void setPriorityScore(int priorityScore) {
			this.priorityScore = priorityScore;
		}
		
		public String getAgeReason() {
			return ageReason;
		}
		
		public void setAgeReason(String ageReason) {
			this.ageReason = ageReason;
		}
		
		public String getHealthReason() {
			return healthReason;
		}
		
		public void setHealthReason(String healthReason) {
			this.healthReason = healthReason;
		}
		
		public String getPriorityReason() {
			return priorityReason;
		}
		
		public void setPriorityReason(String priorityReason) {
			this.priorityReason = priorityReason;
		}
	}
}
