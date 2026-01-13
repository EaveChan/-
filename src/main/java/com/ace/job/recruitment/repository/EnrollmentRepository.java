package com.ace.job.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ace.job.recruitment.entity.EnrollmentApplication;

public interface EnrollmentRepository extends JpaRepository<EnrollmentApplication, Long> {
	List<EnrollmentApplication> findByParticipantId(int participantId);

	List<EnrollmentApplication> findByClinicalTrialId(long trialId);

	List<EnrollmentApplication> findByStatus(String status);

	List<EnrollmentApplication> findByParticipantIdAndClinicalTrialId(int participantId, long trialId);
}
