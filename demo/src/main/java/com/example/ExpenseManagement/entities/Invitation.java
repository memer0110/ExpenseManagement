package com.example.ExpenseManagement.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
@Entity
public class Invitation {

    @Id
    @Column(name = "invitation_id",updatable = false, nullable = false, unique = true)
    private String invitationId;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status = InvitationStatus.PENDING;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "projected_budget")
    private double projectedBudget;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne  // Many Invitations to One Project
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        if (this.invitationId == null) {
            this.invitationId = UUID.randomUUID().toString().replace("-", "");
        }
    }

    public Invitation() {
    }

    public Invitation(String invitationId, String countryCode, Date created, InvitationStatus status, String phoneNumber, double projectedBudget, Date updated, String userName, Project project, User user) {
        this.invitationId = invitationId;
        this.countryCode = countryCode;
        this.created = created;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.projectedBudget = projectedBudget;
        this.updated = updated;
        this.userName = userName;
        this.project = project;
        this.user = user;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getProjectedBudget() {
        return projectedBudget;
    }

    public void setProjectedBudget(double projectedBudget) {
        this.projectedBudget = projectedBudget;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
