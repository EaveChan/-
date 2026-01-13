package com.ace.job.recruitment.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 匹配规则实体
 * 用于存储智能匹配的规则配置
 */
@Entity
@Table(name = "matching_rules")
public class MatchingRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 规则类型：EXCLUSION（排除）、PRIORITY（优先）、REQUIRED（必需）
     */
    @Column(name = "rule_type", nullable = false, length = 20)
    private String ruleType;
    
    /**
     * 关键词（多个关键词用逗号分隔）
     */
    @Column(name = "keywords", nullable = false, columnDefinition = "TEXT")
    private String keywords;
    
    /**
     * 规则描述
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    /**
     * 优先级（数值越大优先级越高）
     */
    @Column(name = "priority", nullable = false)
    private Integer priority;
    
    /**
     * 权重分数（用于优先条件加分）
     */
    @Column(name = "weight_score")
    private Integer weightScore;
    
    /**
     * 是否启用
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    
    /**
     * 创建时间
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 构造函数
    public MatchingRule() {
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRuleType() {
        return ruleType;
    }
    
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getPriority() {
        return priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Integer getWeightScore() {
        return weightScore;
    }
    
    public void setWeightScore(Integer weightScore) {
        this.weightScore = weightScore;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
