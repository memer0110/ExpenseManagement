package com.example.ExpenseManagement.DTO;

import java.time.LocalDateTime;

public class ProjectDTO {
    private String projectId;
    private String projectName;
    private String projectDepartment;
    private String projectLocation;
    private String projectStatus;
    private String projectType;
    private double projectedInvestment;
    private LocalDateTime expectedEndDate;
    private int expectedEndDuration;
    private boolean isDeleted;
    private LocalDateTime startDate;
    private String userId;


    public ProjectDTO() {}

    public ProjectDTO(String projectId, String projectName, String projectDepartment, String projectLocation,
                      String projectStatus, String projectType, double projectedInvestment,
                      LocalDateTime expectedEndDate, int expectedEndDuration, boolean isDeleted,
                      LocalDateTime startDate, String userId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDepartment = projectDepartment;
        this.projectLocation = projectLocation;
        this.projectStatus = projectStatus;
        this.projectType = projectType;
        this.projectedInvestment = projectedInvestment;
        this.expectedEndDate = expectedEndDate;
        this.expectedEndDuration = expectedEndDuration;
        this.isDeleted = isDeleted;
        this.startDate = startDate;
        this.userId = userId;
    }

    // Getters and Setters
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
