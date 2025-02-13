package com.example.ExpenseManagement.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "approval")
@Data
public class Approval {
    
    @Id
    @Column(name = "approval_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String approvalId;
    
    @Column(name = "approval_status")
    private String approvalStatus;
    
    @Column(name = "approval_type")
    private String approvalType;
    
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;
    
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;
    
    @ManyToOne
    @JoinColumn(name = "invitation_id")
    private Invitation invitation;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne
    @JoinColumn(name = "project_edits_id")
    private ProjectEdit projectEdits;
    
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    
}
