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

    @Column(name = "invitation_status")
    private String invitationStatus;

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

}
