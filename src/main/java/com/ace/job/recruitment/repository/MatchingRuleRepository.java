package com.ace.job.recruitment.repository;

import com.ace.job.recruitment.entity.MatchingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 匹配规则仓库接口
 */
@Repository
public interface MatchingRuleRepository extends JpaRepository<MatchingRule, Long> {
    
    /**
     * 根据规则类型查询启用的规则
     */
    List<MatchingRule> findByRuleTypeAndIsActiveTrue(String ruleType);
    
    /**
     * 查询所有启用的规则
     */
    List<MatchingRule> findByIsActiveTrueOrderByPriorityDesc();
}
