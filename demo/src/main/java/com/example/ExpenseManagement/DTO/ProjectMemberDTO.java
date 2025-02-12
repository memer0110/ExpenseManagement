package com.example.ExpenseManagement.DTO;

import java.sql.Timestamp;

public class ProjectMemberDTO {

    private String projectMemberId;
    private Timestamp created;
    private double projectMemberActualShares;
    private double projectMemberInvestment;
    private double projectMemberProfitShares;
    private String projectMemberstatus;
    private String projectMemberType;
    private Timestamp updated;
    private String projectId;  // Only storing project ID, not full Project object
    private String userId;     // Only storing user ID, not full User object
    private String subUserId;

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public double getProjectMemberActualShares() {
        return projectMemberActualShares;
    }

    public void setProjectMemberActualShares(double projectMemberActualShares) {
        this.projectMemberActualShares = projectMemberActualShares;
    }

    public String getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(String projectMemberId) {
        this.projectMemberId = projectMemberId;
    }

    public double getProjectMemberInvestment() {
        return projectMemberInvestment;
    }

    public void setProjectMemberInvestment(double projectMemberInvestment) {
        this.projectMemberInvestment = projectMemberInvestment;
    }

    public double getProjectMemberProfitShares() {
        return projectMemberProfitShares;
    }

    public void setProjectMemberProfitShares(double projectMemberProfitShares) {
        this.projectMemberProfitShares = projectMemberProfitShares;
    }

    public String getProjectMemberstatus() {
        return projectMemberstatus;
    }

    public void setProjectMemberstatus(String projectMemberstatus) {
        this.projectMemberstatus = projectMemberstatus;
    }

    public String getProjectMemberType() {
        return projectMemberType;
    }

    public void setProjectMemberType(String projectMemberType) {
        this.projectMemberType = projectMemberType;
    }

    public String getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(String subUserId) {
        this.subUserId = subUserId;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}