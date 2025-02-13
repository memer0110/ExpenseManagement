package com.example.ExpenseManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users", schema = "expensemanagement")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(name = "country_code")
    private String countryCode;



    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)

    private Timestamp created;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "role")
    private String role;

    @Column(name = "token")
    private String token;

    @UpdateTimestamp
    @Column(name = "updated", nullable = false)
    private Timestamp updated;


    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "userstatus", nullable = false)
    private Boolean userStatus;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;


    public User() {}

    public User(String userId2) {
		this.userId = userId2;
	}

	public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

}

