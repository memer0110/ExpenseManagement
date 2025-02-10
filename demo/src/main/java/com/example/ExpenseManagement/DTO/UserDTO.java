package com.example.ExpenseManagement.DTO;

import java.sql.Timestamp;

public class UserDTO {
    private String userId;
    private String countryCode;
    private Timestamp created;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String imgUrl;
    private Boolean isDeleted;
    private String phoneNo;
    private String role;
    private Timestamp updated;
    private Boolean userStatus;

   
    public UserDTO() {}

    
    public UserDTO(String userId, String countryCode, Timestamp created, String email, String firstName, 
                   String lastName, String gender, String imgUrl, Boolean isDeleted, String phoneNo, 
                   String role, Timestamp updated, Boolean userStatus) {
        this.userId = userId;
        this.countryCode = countryCode;
        this.created = created;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.isDeleted = isDeleted;
        this.phoneNo = phoneNo;
        this.role = role;
        this.updated = updated;
        this.userStatus = userStatus;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }
}

