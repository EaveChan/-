package com.ace.job.recruitment.dto;

/**
 * 匹配配置DTO
 * 用于配置匹配算法的权重和阈值
 */
public class MatchingConfig {
    
    // 基础分
    private int baseScore = 60;
    
    // 年龄权重（最大分数）
    private int ageWeight = 20;
    
    // 健康状况权重（最大分数）
    private int healthWeight = 20;
    
    // 优先条件权重（最大分数）
    private int priorityWeight = 10;
    
    // 推荐阈值（低于此分数不推荐）
    private int recommendThreshold = 60;
    
    // 是否启用排除规则
    private boolean enableExclusionRules = true;
    
    // 是否启用优先规则
    private boolean enablePriorityRules = true;
    
    // 构造函数
    public MatchingConfig() {
    }
    
    // Getters and Setters
    public int getBaseScore() {
        return baseScore;
    }
    
    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }
    
    public int getAgeWeight() {
        return ageWeight;
    }
    
    public void setAgeWeight(int ageWeight) {
        this.ageWeight = ageWeight;
    }
    
    public int getHealthWeight() {
        return healthWeight;
    }
    
    public void setHealthWeight(int healthWeight) {
        this.healthWeight = healthWeight;
    }
    
    public int getPriorityWeight() {
        return priorityWeight;
    }
    
    public void setPriorityWeight(int priorityWeight) {
        this.priorityWeight = priorityWeight;
    }
    
    public int getRecommendThreshold() {
        return recommendThreshold;
    }
    
    public void setRecommendThreshold(int recommendThreshold) {
        this.recommendThreshold = recommendThreshold;
    }
    
    public boolean isEnableExclusionRules() {
        return enableExclusionRules;
    }
    
    public void setEnableExclusionRules(boolean enableExclusionRules) {
        this.enableExclusionRules = enableExclusionRules;
    }
    
    public boolean isEnablePriorityRules() {
        return enablePriorityRules;
    }
    
    public void setEnablePriorityRules(boolean enablePriorityRules) {
        this.enablePriorityRules = enablePriorityRules;
    }
}
