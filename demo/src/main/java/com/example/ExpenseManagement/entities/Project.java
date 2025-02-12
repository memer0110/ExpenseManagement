package com.example.ExpenseManagement.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;


import jakarta.persistence.*;

import lombok.Setter;


@Entity
@Table(name = "projects", schema = "expensemanagement")
public class Project {

    @Id
    @Column(name = "project_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String projectId;

    @Column(name = "created", nullable = false, updatable = false)
    private Timestamp created;

    @Column(name = "expected_end_date")
    private LocalDateTime expectedEndDate;

    @Setter
    @Column(name = "expected_end_duration", nullable = false)
    private int expectedEndDuration;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "project_department", nullable = false)
    private String projectDepartment;

    @Column(name = "project_location", nullable = false)
    private String projectLocation;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_status", nullable = false)
    private String projectStatus = "created";

    @Column(name = "project_type", nullable = false)
    private String projectType;

    @Column(name = "projected_investment", nullable = false)
    private double projectedInvestment;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "updated", nullable = false, insertable = false)
    private Timestamp updated;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public LocalDateTime getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(LocalDateTime expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public int getExpectedEndDuration() {
		return expectedEndDuration;
	}
	

    public void setExpectedEndDuration(int expectedEndDuration) {
		this.expectedEndDuration = expectedEndDuration;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getProjectDepartment() {
		return projectDepartment;
	}

	public void setProjectDepartment(String projectDepartment) {
		this.projectDepartment = projectDepartment;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public double getProjectedInvestment() {
		return projectedInvestment;
	}

	public void setProjectedInvestment(double projectedInvestment) {
		this.projectedInvestment = projectedInvestment;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public void setExpectedEndDuration(int expectedEndDuration) {
	}
}
