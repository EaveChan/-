package com.ace.job.recruitment.service;

import java.util.List;

import com.ace.job.recruitment.entity.User;

/**
 * 受试者服务接口
 */
public interface SubjectService {

	/**
	 * 获取所有受试者（角色为PARTICIPANT的用户）
	 * 
	 * @return 受试者列表
	 */
	List<User> getAllSubjects();

	/**
	 * 根据ID获取受试者
	 * 
	 * @param id 受试者ID
	 * @return 受试者对象
	 */
	User getSubjectById(int id);

	/**
	 * 创建受试者
	 * 
	 * @param subject 受试者对象
	 * @return 创建后的受试者对象
	 */
	User createSubject(User subject);

	/**
	 * 更新受试者信息
	 * 
	 * @param id      受试者ID
	 * @param subject 受试者对象
	 * @return 更新后的受试者对象
	 */
	User updateSubject(int id, User subject);

	/**
	 * 根据状态获取受试者
	 * 
	 * @param status 状态
	 * @return 受试者列表
	 */
	List<User> getSubjectsByStatus(boolean status);

	/**
	 * 根据状态字符串获取受试者
	 * 
	 * @param status 状态字符串
	 * @return 受试者列表
	 */
	List<User> getSubjectsByStatus(String status);

	/**
	 * 搜索受试者
	 * 
	 * @param keyword 关键词
	 * @return 受试者列表
	 */
	List<User> searchSubjects(String keyword);

	/**
	 * 获取所有可用的受试者（状态为活跃且角色为PARTICIPANT）
	 * 
	 * @return 可用受试者列表
	 */
	List<User> getAvailableSubjects();
}
