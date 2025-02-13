package com.example.ExpenseManagement.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "project_edits", schema = "expensemanagement")
public class ProjectEdit {

    @Id
    @Column(name = "project_edits_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
    private String projectEditsId;

    @Column(name = "created", nullable = false, updatable = false, insertable = false)
    private Timestamp created;

    @Column(name = "edited_project_department", nullable = false)
    private String editedProjectDepartment;

    @Column(name = "edited_project_location", nullable = false)
    private String editedProjectLocation;

    @Column(name = "edited_project_name", nullable = false)
    private String editedProjectName;

    @Column(name = "edited_project_status", nullable = false)
    private String editedProjectStatus = "created";

    @Column(name = "edited_project_type", nullable = false)
    private String editedProjectType;

    @Column(name = "edited_projected_investment", nullable = false)
    private double editedProjectedInvestment;

    @Column(name = "edited_start_date", nullable = false)
    private LocalDateTime editedStartDate;

    @Column(name = "editedexpected_end_date")
    private LocalDateTime editedExpectedEndDate;

    @Column(name = "editedexpected_end_duration", nullable = false)
    private int editedExpectedEndDuration;

    @Column(name = "project_edited_status")
    private String projectEditedStatus;

    @Column(name = "updated", nullable = false, insertable = false)
    private Timestamp updated;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

	public String getProjectEditsId() {
		return projectEditsId;
	}

	public void setProjectEditsId(String projectEditsId) {
		this.projectEditsId = projectEditsId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getEditedProjectDepartment() {
		return editedProjectDepartment;
	}

	public void setEditedProjectDepartment(String editedProjectDepartment) {
		this.editedProjectDepartment = editedProjectDepartment;
	}

	public String getEditedProjectLocation() {
		return editedProjectLocation;
	}

	public void setEditedProjectLocation(String editedProjectLocation) {
		this.editedProjectLocation = editedProjectLocation;
	}

	public String getEditedProjectName() {
		return editedProjectName;
	}

	public void setEditedProjectName(String editedProjectName) {
		this.editedProjectName = editedProjectName;
	}

	public String getEditedProjectStatus() {
		return editedProjectStatus;
	}

	public void setEditedProjectStatus(String editedProjectStatus) {
		this.editedProjectStatus = editedProjectStatus;
	}

	public String getEditedProjectType() {
		return editedProjectType;
	}

	public void setEditedProjectType(String editedProjectType) {
		this.editedProjectType = editedProjectType;
	}

	public double getEditedProjectedInvestment() {
		return editedProjectedInvestment;
	}

	public void setEditedProjectedInvestment(double editedProjectedInvestment) {
		this.editedProjectedInvestment = editedProjectedInvestment;
	}

	public LocalDateTime getEditedStartDate() {
		return editedStartDate;
	}

	public void setEditedStartDate(LocalDateTime editedStartDate) {
		this.editedStartDate = editedStartDate;
	}

	public LocalDateTime getEditedExpectedEndDate() {
		return editedExpectedEndDate;
	}

	public void setEditedExpectedEndDate(LocalDateTime editedExpectedEndDate) {
		this.editedExpectedEndDate = editedExpectedEndDate;
	}

	public int getEditedExpectedEndDuration() {
		return editedExpectedEndDuration;
	}

	public void setEditedExpectedEndDuration(int editedExpectedEndDuration) {
		this.editedExpectedEndDuration = editedExpectedEndDuration;
	}

	public String getProjectEditedStatus() {
		return projectEditedStatus;
	}

	public void setProjectEditedStatus(String projectEditedStatus) {
		this.projectEditedStatus = projectEditedStatus;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
    
    
    
}
