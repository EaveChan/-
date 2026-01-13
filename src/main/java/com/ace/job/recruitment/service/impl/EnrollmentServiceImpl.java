package com.ace.job.recruitment.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.entity.EnrollmentApplication;
import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.repository.ClinicalTrialRepository;
import com.ace.job.recruitment.repository.EnrollmentRepository;
import com.ace.job.recruitment.repository.UserRepository;
import com.ace.job.recruitment.service.EnrollmentService;
import com.ace.job.recruitment.service.MatchingService;

/**
 * 入组服务实现类
 */
@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClinicalTrialRepository clinicalTrialRepository;

	@Autowired
	private MatchingService matchingService;

	@Override
	public EnrollmentApplication createApplication(int subjectId, Long trialId) {
		User subject = userRepository.findById(subjectId).orElse(null);
		ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElse(null);

		if (subject == null || trial == null) {
			throw new IllegalArgumentException("受试者或临床试验不存在");
		}

		// 检查是否已经申请过
		List<EnrollmentApplication> existingApps = enrollmentRepository.findByParticipantIdAndClinicalTrialId(subjectId,
				trialId);
		if (!existingApps.isEmpty()) {
			throw new IllegalStateException("该受试者已经申请过此临床试验");
		}

		// 计算匹配分数
		int matchScore = matchingService.calculateMatchingScore(subject, trial);

		// 创建申请
		EnrollmentApplication application = new EnrollmentApplication();
		application.setParticipant(subject);
		application.setClinicalTrial(trial);
		application.setMatchScore(matchScore);
		application.setStatus("PENDING"); // 待审核
		application.setCreatedAt(LocalDateTime.now());

		return enrollmentRepository.save(application);
	}

	@Override
	public EnrollmentApplication approveEnrollment(Long applicationId, User reviewer, String notes) {
		EnrollmentApplication application = enrollmentRepository.findById(applicationId).orElse(null);
		if (application == null) {
			throw new IllegalArgumentException("申请不存在");
		}

		application.setStatus("APPROVED");
		application.setReviewedBy(reviewer);
		application.setReviewNotes(notes);
		application.setReviewedAt(LocalDateTime.now());

		return enrollmentRepository.save(application);
	}

	@Override
	public EnrollmentApplication rejectEnrollment(Long applicationId, User reviewer, String notes) {
		EnrollmentApplication application = enrollmentRepository.findById(applicationId).orElse(null);
		if (application == null) {
			throw new IllegalArgumentException("申请不存在");
		}

		application.setStatus("REJECTED");
		application.setReviewedBy(reviewer);
		application.setReviewNotes(notes);
		application.setReviewedAt(LocalDateTime.now());

		return enrollmentRepository.save(application);
	}

	@Override
	public List<EnrollmentApplication> getPendingApplications() {
		return enrollmentRepository.findByStatus("PENDING");
	}

	@Override
	public List<EnrollmentApplication> getAllApplications() {
		return enrollmentRepository.findAll();
	}

	@Override
	public List<EnrollmentApplication> getApplicationsBySubject(int subjectId) {
		return enrollmentRepository.findByParticipantId(subjectId);
	}

	@Override
	public List<EnrollmentApplication> getApplicationsByTrial(Long trialId) {
		return enrollmentRepository.findByClinicalTrialId(trialId);
	}

	@Override
	public EnrollmentApplication getApplicationById(Long id) {
		return enrollmentRepository.findById(id).orElse(null);
	}

	@Override
	public EnrollmentApplication updateApplicationStatus(Long applicationId, String status) {
		EnrollmentApplication application = enrollmentRepository.findById(applicationId).orElse(null);
		if (application == null) {
			throw new IllegalArgumentException("申请不存在");
		}

		application.setStatus(status);
		return enrollmentRepository.save(application);
	}

	@Override
	public void rejectSubjectForTrial(int subjectId, Long trialId) {
		User subject = userRepository.findById(subjectId).orElse(null);
		ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElse(null);

		if (subject == null || trial == null) {
			throw new IllegalArgumentException("受试者或临床试验不存在");
		}

		// 检查是否已有申请
		List<EnrollmentApplication> existingApps = enrollmentRepository.findByParticipantIdAndClinicalTrialId(subjectId,
				trialId);

		if (existingApps.isEmpty()) {
			// 如果没有申请，创建一个拒绝的申请记录
			EnrollmentApplication application = new EnrollmentApplication();
			application.setParticipant(subject);
			application.setClinicalTrial(trial);
			application.setMatchScore(0);
			application.setStatus("REJECTED");
			application.setReviewNotes("从匹配结果页面直接拒绝");
			application.setCreatedAt(LocalDateTime.now());
			application.setReviewedAt(LocalDateTime.now());
			enrollmentRepository.save(application);
		} else {
			// 如果已有申请，更新为拒绝状态
			EnrollmentApplication application = existingApps.get(0);
			application.setStatus("REJECTED");
			application.setReviewNotes("从匹配结果页面直接拒绝");
			application.setReviewedAt(LocalDateTime.now());
			enrollmentRepository.save(application);
		}
	}

	@Override
	public void markForManualReview(int subjectId, Long trialId) {
		User subject = userRepository.findById(subjectId).orElse(null);
		ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElse(null);

		if (subject == null || trial == null) {
			throw new IllegalArgumentException("受试者或临床试验不存在");
		}

		// 检查是否已有申请
		List<EnrollmentApplication> existingApps = enrollmentRepository.findByParticipantIdAndClinicalTrialId(subjectId,
				trialId);

		if (existingApps.isEmpty()) {
			// 如果没有申请，创建一个需要人工复核的申请
			int matchScore = matchingService.calculateMatchingScore(subject, trial);
			EnrollmentApplication application = new EnrollmentApplication();
			application.setParticipant(subject);
			application.setClinicalTrial(trial);
			application.setMatchScore(matchScore);
			application.setStatus("MANUAL_REVIEW");
			application.setReviewNotes("标记为需要人工复核");
			application.setCreatedAt(LocalDateTime.now());
			enrollmentRepository.save(application);
		} else {
			// 如果已有申请，更新为人工复核状态
			EnrollmentApplication application = existingApps.get(0);
			application.setStatus("MANUAL_REVIEW");
			application.setReviewNotes("标记为需要人工复核");
			enrollmentRepository.save(application);
		}
	}

	@Override
	public List<EnrollmentApplication> getApplicationsByUserId(int userId) {
		return enrollmentRepository.findByParticipantId(userId);
	}

	@Override
	public EnrollmentApplication getApplicationByUserAndTrial(int userId, Long trialId) {
		List<EnrollmentApplication> applications = enrollmentRepository.findByParticipantIdAndClinicalTrialId(userId,
				trialId);
		return applications.isEmpty() ? null : applications.get(0);
	}

	@Override
	public void deleteApplication(Long applicationId) {
		enrollmentRepository.deleteById(applicationId);
	}
}
