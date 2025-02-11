package com.example.ExpenseManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDTO {
    private String approvalId;
    private String approvalStatus;
    private String approvalType;
    private String invitationId;
    private String projectId;
    private String userId;
    private LocalDateTime created;
    private LocalDateTime updated;
}

