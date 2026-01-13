package com.ace.job.recruitment.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "enrollment_applications")
public class EnrollmentApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "participant_id", nullable = false)
	@JsonManagedReference
	private User participant;

	@ManyToOne
	@JoinColumn(name = "trial_id", nullable = false)
	@JsonManagedReference
	private ClinicalTrial clinicalTrial;

	@Column(nullable = false)
	private Integer matchScore;

	@Column(length = 20, nullable = false)
	private String status;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "reviewed_by")
	@JsonManagedReference
	private User reviewedBy;

	@Column(name = "reviewed_at")
	private LocalDateTime reviewedAt;

	@Column(columnDefinition = "TEXT")
	private String reviewNotes;

	public EnrollmentApplication() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public ClinicalTrial getClinicalTrial() {
		return clinicalTrial;
	}

	public void setClinicalTrial(ClinicalTrial clinicalTrial) {
		this.clinicalTrial = clinicalTrial;
	}

	public Integer getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(Integer matchScore) {
		this.matchScore = matchScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(User reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public LocalDateTime getReviewedAt() {
		return reviewedAt;
	}

	public void setReviewedAt(LocalDateTime reviewedAt) {
		this.reviewedAt = reviewedAt;
	}

	public String getReviewNotes() {
		return reviewNotes;
	}

	public void setReviewNotes(String reviewNotes) {
		this.reviewNotes = reviewNotes;
	}
}
