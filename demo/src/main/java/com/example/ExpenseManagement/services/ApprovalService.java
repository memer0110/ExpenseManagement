package com.example.ExpenseManagement.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ExpenseManagement.DTO.ApprovalDTO;
import com.example.ExpenseManagement.entities.Approval;
import com.example.ExpenseManagement.entities.Invitation;
import com.example.ExpenseManagement.entities.InvitationStatus;
import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.ApprovalRepository;
import com.example.ExpenseManagement.repositories.InvitationRepository;
import com.example.ExpenseManagement.repositories.UserRepository;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;


    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ApprovalDTO approveInvitation(String token, String invitationId, String status) {

        String userId = jwtService.extractUserId(token);

        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> {
                    return new RuntimeException("Invitation not found");
                });

        Approval approval = new Approval();
        approval.setApprovalStatus(status);
        approval.setApprovalType("INVITATION");
        approval.setCreated(LocalDateTime.now());
        approval.setUpdated(LocalDateTime.now());
        approval.setInvitation(invitation);
        approval.setProject(invitation.getProject());
        approval.setUser(new User(userId)); 

        approvalRepository.save(approval);
        
        InvitationStatus invitationStatus;
        try {
            invitationStatus = InvitationStatus.valueOf(status.toUpperCase()); 
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid invitation status: " + status);
        }

        invitationService.updateInvitationStatus(invitationId, invitationStatus);

        ApprovalDTO responseDTO = new ApprovalDTO(
                approval.getApprovalId(),
                approval.getApprovalStatus(),
                approval.getApprovalType(),
                invitation.getInvitationId(),
                invitation.getProject().getProjectId(),
                userId,
                approval.getCreated(),
                approval.getUpdated()
        );

        return responseDTO;
    }
}
