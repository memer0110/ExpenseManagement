package com.example.ExpenseManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

public class ProjectMember {

    private String project_members_shares_id;
    private Date created;
    private String member_share_status ;
    private double project_member_actual_shares ;
    private double project_member_profit_shares;
    private Date updated;
    private String project_member_id;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getProject_members_shares_id() {
        return project_members_shares_id;
    }

    public void setProject_members_shares_id(String project_members_shares_id) {
        this.project_members_shares_id = project_members_shares_id;
    }

    public double getProject_member_profit_shares() {
        return project_member_profit_shares;
    }

    public void setProject_member_profit_shares(double project_member_profit_shares) {
        this.project_member_profit_shares = project_member_profit_shares;
    }

    public String getProject_member_id() {
        return project_member_id;
    }

    public void setProject_member_id(String project_member_id) {
        this.project_member_id = project_member_id;
    }

    public double getProject_member_actual_shares() {
        return project_member_actual_shares;
    }

    public void setProject_member_actual_shares(double project_member_actual_shares) {
        this.project_member_actual_shares = project_member_actual_shares;
    }

    public String getMember_share_status() {
        return member_share_status;
    }

    public void setMember_share_status(String member_share_status) {
        this.member_share_status = member_share_status;
    }
}
