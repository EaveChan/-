package com.ace.job.recruitment.service;

import java.util.List;

import com.ace.job.recruitment.entity.ClinicalTrial;

/**
 * 临床试验服务接口
 */
public interface ClinicalTrialService {

	/**
	 * 获取所有临床试验
	 * 
	 * @return 临床试验列表
	 */
	List<ClinicalTrial> getAllTrials();

	/**
	 * 根据ID获取临床试验
	 * 
	 * @param id 临床试验ID
	 * @return 临床试验对象
	 */
	ClinicalTrial getTrialById(Long id);

	/**
	 * 创建临床试验
	 * 
	 * @param trial 临床试验对象
	 * @return 创建后的临床试验对象
	 */
	ClinicalTrial createTrial(ClinicalTrial trial);

	/**
	 * 更新临床试验
	 * 
	 * @param id    临床试验ID
	 * @param trial 临床试验对象
	 * @return 更新后的临床试验对象
	 */
	ClinicalTrial updateTrial(Long id, ClinicalTrial trial);

	/**
	 * 关闭临床试验
	 * 
	 * @param id 临床试验ID
	 */
	void closeTrial(Long id);

	/**
	 * 获取所有活跃的临床试验
	 * 
	 * @return 活跃的临床试验列表
	 */
	List<ClinicalTrial> getActiveTrials();

	/**
	 * 获取临床试验的已入组人数
	 * 
	 * @param trialId 临床试验ID
	 * @return 已入组人数
	 */
	int getEnrolledCount(Long trialId);

	/**
	 * 根据状态获取临床试验
	 * 
	 * @param status 状态
	 * @return 临床试验列表
	 */
	List<ClinicalTrial> getTrialsByStatus(String status);

	/**
	 * 删除临床试验
	 * 
	 * @param id 临床试验ID
	 */
	void deleteTrial(Long id);
}
