package com.ace.job.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ace.job.recruitment.entity.ClinicalTrial;

public interface ClinicalTrialRepository extends JpaRepository<ClinicalTrial, Long> {
	List<ClinicalTrial> findByStatus(String status);
}
