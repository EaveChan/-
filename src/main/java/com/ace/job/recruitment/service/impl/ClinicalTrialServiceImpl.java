package com.ace.job.recruitment.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ace.job.recruitment.entity.ClinicalTrial;
import com.ace.job.recruitment.entity.EnrollmentApplication;
import com.ace.job.recruitment.repository.ClinicalTrialRepository;
import com.ace.job.recruitment.repository.EnrollmentRepository;
import com.ace.job.recruitment.service.ClinicalTrialService;

/**
 * 临床试验服务实现类
 */
@Service
@Transactional
public class ClinicalTrialServiceImpl implements ClinicalTrialService {

	@Autowired
	private ClinicalTrialRepository clinicalTrialRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public List<ClinicalTrial> getAllTrials() {
		return clinicalTrialRepository.findAll();
	}

	@Override
	public ClinicalTrial getTrialById(Long id) {
		return clinicalTrialRepository.findById(id).orElse(null);
	}

	@Override
	public ClinicalTrial createTrial(ClinicalTrial trial) {
		// 设置创建时间
		trial.setCreatedDate(LocalDate.now());
		trial.setCreatedTime(LocalTime.now());

		// 设置默认状态为招募中
		if (trial.getStatus() == null || trial.getStatus().isEmpty()) {
			trial.setStatus("RECRUITING");
		}

		return clinicalTrialRepository.save(trial);
	}

	@Override
	public ClinicalTrial updateTrial(Long id, ClinicalTrial trial) {
		ClinicalTrial existingTrial = clinicalTrialRepository.findById(id).orElse(null);
		if (existingTrial == null) {
			return null;
		}

		// 更新字段
		existingTrial.setTrialName(trial.getTrialName());
		existingTrial.setTrialDescription(trial.getTrialDescription());
		existingTrial.setAgeMin(trial.getAgeMin());
		existingTrial.setAgeMax(trial.getAgeMax());
		existingTrial.setGenderRequirement(trial.getGenderRequirement());
		existingTrial.setRequiredConditions(trial.getRequiredConditions());
		existingTrial.setExpectedSubjectCount(trial.getExpectedSubjectCount());
		existingTrial.setTrialType(trial.getTrialType());
		existingTrial.setDuration(trial.getDuration());
		existingTrial.setTrialPhase(trial.getTrialPhase());
		existingTrial.setInstitution(trial.getInstitution());

		// 设置更新时间
		existingTrial.setUpdatedDate(LocalDate.now());
		existingTrial.setUpdatedTime(LocalTime.now());

		return clinicalTrialRepository.save(existingTrial);
	}

	@Override
	public void closeTrial(Long id) {
		ClinicalTrial trial = clinicalTrialRepository.findById(id).orElse(null);
		if (trial != null) {
			trial.setStatus("CLOSED");
			trial.setUpdatedDate(LocalDate.now());
			trial.setUpdatedTime(LocalTime.now());
			clinicalTrialRepository.save(trial);
		}
	}

	@Override
	public List<ClinicalTrial> getActiveTrials() {
		return clinicalTrialRepository.findByStatus("RECRUITING");
	}

	@Override
	public int getEnrolledCount(Long trialId) {
		List<EnrollmentApplication> applications = enrollmentRepository.findByClinicalTrialId(trialId);
		// 统计状态为 APPROVED 或 ENROLLED 的申请
		return (int) applications.stream()
				.filter(app -> "APPROVED".equals(app.getStatus()) || "ENROLLED".equals(app.getStatus())).count();
	}

	@Override
	public List<ClinicalTrial> getTrialsByStatus(String status) {
		return clinicalTrialRepository.findByStatus(status.toUpperCase());
	}

	@Override
	public void deleteTrial(Long id) {
		ClinicalTrial trial = clinicalTrialRepository.findById(id).orElse(null);
		if (trial != null) {
			// 删除相关的入组申请
			List<EnrollmentApplication> applications = enrollmentRepository.findByClinicalTrialId(id);
			if (!applications.isEmpty()) {
				enrollmentRepository.deleteAll(applications);
			}
			// 删除临床试验
			clinicalTrialRepository.delete(trial);
		}
	}
}
