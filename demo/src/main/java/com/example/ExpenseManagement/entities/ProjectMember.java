package com.example.ExpenseManagement.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "project_members")
public class ProjectMember {
    @Id
    @Column(name = "project_member_id")
    private String projectMemberId;
    @Column(name = "created")
    private Timestamp created ;
    @Column(name = "project_member_actual_shares")
    private double projectMemberActualShares;
    @Column(name = "project_member_investment")
    private double projectMemberInvestment ;
    @Column(name = "project_member_profit_shares")
    private double projectMemberProfitShares ;
    @Column(name = "project_member_status")
    private String projectMemberstatus ;
    @Column(name = "project_member_type")
    private String projectMemberType ;
    @Column(name = "updated")
    private Timestamp updated ;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;

    @Column(name = "sub_user_id",nullable = true)
    private String subUserId ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(String subUserId) {
        this.subUserId = subUserId;
    }

    public String getProjectMemberType() {
        return projectMemberType;
    }

    public void setProjectMemberType(String projectMemberType) {
        this.projectMemberType = projectMemberType;
    }

    public String getProjectMemberstatus() {
        return projectMemberstatus;
    }

    public void setProjectMemberstatus(String projectMemberstatus) {
        this.projectMemberstatus = projectMemberstatus;
    }

    public double getProjectMemberProfitShares() {
        return projectMemberProfitShares;
    }

    public void setProjectMemberProfitShares(double projectMemberProfitShares) {
        this.projectMemberProfitShares = projectMemberProfitShares;
    }

    public double getProjectMemberInvestment() {
        return projectMemberInvestment;
    }

    public void setProjectMemberInvestment(double projectMemberInvestment) {
        this.projectMemberInvestment = projectMemberInvestment;
    }

    public String getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(String projectMemberId) {
        this.projectMemberId = projectMemberId;
    }

    public double getProjectMemberActualShares() {
        return projectMemberActualShares;
    }

    public void setProjectMemberActualShares(double projectMemberActualShares) {
        this.projectMemberActualShares = projectMemberActualShares;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
