package com.ace.job.recruitment.service;

import java.util.List;

import com.ace.job.recruitment.entity.EnrollmentApplication;
import com.ace.job.recruitment.entity.User;

/**
 * 入组服务接口
 */
public interface EnrollmentService {

	/**
	 * 创建入组申请
	 * 
	 * @param subjectId 受试者ID
	 * @param trialId   临床试验ID
	 * @return 创建的入组申请
	 */
	EnrollmentApplication createApplication(int subjectId, Long trialId);

	/**
	 * 批准入组
	 * 
	 * @param applicationId 申请ID
	 * @param reviewer      审核人
	 * @param notes         审核备注
	 * @return 更新后的申请
	 */
	EnrollmentApplication approveEnrollment(Long applicationId, User reviewer, String notes);

	/**
	 * 拒绝入组
	 * 
	 * @param applicationId 申请ID
	 * @param reviewer      审核人
	 * @param notes         拒绝原因
	 * @return 更新后的申请
	 */
	EnrollmentApplication rejectEnrollment(Long applicationId, User reviewer, String notes);

	/**
	 * 获取待审核的申请
	 * 
	 * @return 待审核申请列表
	 */
	List<EnrollmentApplication> getPendingApplications();

	/**
	 * 获取所有申请
	 * 
	 * @return 所有申请列表
	 */
	List<EnrollmentApplication> getAllApplications();

	/**
	 * 根据受试者ID获取申请
	 * 
	 * @param subjectId 受试者ID
	 * @return 申请列表
	 */
	List<EnrollmentApplication> getApplicationsBySubject(int subjectId);

	/**
	 * 根据临床试验ID获取申请
	 * 
	 * @param trialId 临床试验ID
	 * @return 申请列表
	 */
	List<EnrollmentApplication> getApplicationsByTrial(Long trialId);

	/**
	 * 根据ID获取申请
	 * 
	 * @param id 申请ID
	 * @return 申请对象
	 */
	EnrollmentApplication getApplicationById(Long id);

	/**
	 * 更新申请状态
	 * 
	 * @param applicationId 申请ID
	 * @param status        新状态
	 * @return 更新后的申请
	 */
	EnrollmentApplication updateApplicationStatus(Long applicationId, String status);

	/**
	 * 拒绝受试者参与某个临床试验
	 * 
	 * @param subjectId 受试者ID
	 * @param trialId   临床试验ID
	 */
	void rejectSubjectForTrial(int subjectId, Long trialId);

	/**
	 * 标记受试者需要人工复核
	 * 
	 * @param subjectId 受试者ID
	 * @param trialId   临床试验ID
	 */
	void markForManualReview(int subjectId, Long trialId);

	/**
	 * 根据用户ID获取申请列表
	 * 
	 * @param userId 用户ID
	 * @return 申请列表
	 */
	List<EnrollmentApplication> getApplicationsByUserId(int userId);

	/**
	 * 根据用户ID和临床试验ID获取申请
	 * 
	 * @param userId  用户ID
	 * @param trialId 临床试验ID
	 * @return 申请对象，如果不存在返回null
	 */
	EnrollmentApplication getApplicationByUserAndTrial(int userId, Long trialId);

	/**
	 * 删除申请
	 * 
	 * @param applicationId 申请ID
	 */
	void deleteApplication(Long applicationId);
}
