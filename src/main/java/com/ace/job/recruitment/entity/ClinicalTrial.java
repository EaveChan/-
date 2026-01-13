package com.ace.job.recruitment.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "clinical_trials")
public class ClinicalTrial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 100, nullable = false)
	private String trialName;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String trialDescription;
	@Column(nullable = false)
	private Integer ageMin;
	@Column(nullable = false)
	private Integer ageMax;
	@Column(length = 10, nullable = false)
	private String genderRequirement;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String requiredConditions;
	@Column(columnDefinition = "TEXT")
	private String exclusionCriteria;
	@Column(length = 20, nullable = false)
	private String status;

	@Column(length = 50, nullable = false)
	private String trialType;

	@Column(nullable = false)
	private Integer duration;

	@Column(name = "expected_subject_count", nullable = false)
	private Integer expectedSubjectCount;

	@Column(length = 20)
	private String trialPhase;

	@Column(length = 100)
	private String institution;

	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

	@Column(name = "created_time", nullable = false)
	private LocalTime createdTime;

	@Column(name = "updated_date")
	private LocalDate updatedDate;

	@Column(name = "updated_time")
	private LocalTime updatedTime;

	@Column(name = "created_user_id", nullable = false)
	private int createdUserId;

	@Column(name = "updated_user_id")
	private int updatedUserId;

	@OneToMany(mappedBy = "clinicalTrial", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<EnrollmentApplication> enrollmentApplications;

	public ClinicalTrial() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrialName() {
		return trialName;
	}

	public void setTrialName(String trialName) {
		this.trialName = trialName;
	}

	public String getTrialDescription() {
		return trialDescription;
	}

	public void setTrialDescription(String trialDescription) {
		this.trialDescription = trialDescription;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public String getGenderRequirement() {
		return genderRequirement;
	}

	public void setGenderRequirement(String genderRequirement) {
		this.genderRequirement = genderRequirement;
	}

	public String getRequiredConditions() {
		return requiredConditions;
	}

	public void setRequiredConditions(String requiredConditions) {
		this.requiredConditions = requiredConditions;
	}

	public String getExclusionCriteria() {
		return exclusionCriteria;
	}

	public void setExclusionCriteria(String exclusionCriteria) {
		this.exclusionCriteria = exclusionCriteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrialType() {
		return trialType;
	}

	public void setTrialType(String trialType) {
		this.trialType = trialType;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getExpectedSubjectCount() {
		return expectedSubjectCount;
	}

	public void setExpectedSubjectCount(Integer expectedSubjectCount) {
		this.expectedSubjectCount = expectedSubjectCount;
	}

	public String getTrialPhase() {
		return trialPhase;
	}

	public void setTrialPhase(String trialPhase) {
		this.trialPhase = trialPhase;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public LocalTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public int getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(int updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public List<EnrollmentApplication> getEnrollmentApplications() {
		return enrollmentApplications;
	}

	public void setEnrollmentApplications(List<EnrollmentApplication> enrollmentApplications) {
		this.enrollmentApplications = enrollmentApplications;
	}
}
