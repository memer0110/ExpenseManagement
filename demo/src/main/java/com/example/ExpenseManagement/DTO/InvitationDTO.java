package com.example.ExpenseManagement.DTO;


import com.example.ExpenseManagement.entities.InvitationStatus;

public class InvitationDTO {
    private String name;
    private String projectId;
    private String contactNumber;
    private String countryCode;
    private Double projectedAmount;
    private InvitationStatus status;

    public InvitationDTO() {
    }

    public InvitationDTO(String name, String projectId, String contactNumber, String countryCode, Double projectedAmount, InvitationStatus status) {
        this.name = name;
        this.projectId = projectId;
        this.contactNumber = contactNumber;
        this.countryCode = countryCode;
        this.projectedAmount = projectedAmount;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getProjectedAmount() {
        return projectedAmount;
    }

    public void setProjectedAmount(Double projectedAmount) {
        this.projectedAmount = projectedAmount;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
