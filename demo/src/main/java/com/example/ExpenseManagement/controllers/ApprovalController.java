package com.example.ExpenseManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExpenseManagement.DTO.ApprovalDTO;
import com.example.ExpenseManagement.entities.Approval;
import com.example.ExpenseManagement.services.ApprovalService;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

	@Autowired
    private ApprovalService approvalService;

    @PostMapping("/approve-invitation")
    public ResponseEntity<ApprovalDTO> approveInvitation(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam String invitationId,
            @RequestParam String status) {

        String token = authorizationHeader.replace("Bearer ", "");

        ApprovalDTO createdApproval = approvalService.approveInvitation(token, invitationId, status);
        
        return ResponseEntity.ok(createdApproval);
    }
}
