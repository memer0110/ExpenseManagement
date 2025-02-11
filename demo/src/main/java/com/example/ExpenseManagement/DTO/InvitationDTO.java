package com.example.ExpenseManagement.DTO;


public class InvitationDTO {
    private String name;
    private String projectId;
    private String contactNumber;
    private Double projectedAmount;

    public InvitationDTO() {
    }

    public InvitationDTO(String name, String projectId, String contactNumber, Double projectedAmount) {
        this.name = name;
        this.projectId = projectId;
        this.contactNumber = contactNumber;
        this.projectedAmount = projectedAmount;
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

    public Double getProjectedAmount() {
        return projectedAmount;
    }

    public void setProjectedAmount(Double projectedAmount) {
        this.projectedAmount = projectedAmount;
    }
}
