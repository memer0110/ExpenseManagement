package com.example.ExpenseManagement;

import com.example.ExpenseManagement.DTO.InvitationDTO;
import com.example.ExpenseManagement.entities.Invitation;

import java.util.List;

public interface InvitationImpl {
    Invitation sendInvitation(String token, InvitationDTO invitationDTO);
    List<Invitation> getAllInvitation();


}
