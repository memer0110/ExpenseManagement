package com.example.ExpenseManagement;

import com.example.ExpenseManagement.DTO.InvitationDTO;

public interface InvitationImpl {
    String sendInvitation(String token,  InvitationDTO invitationDTO);
}
