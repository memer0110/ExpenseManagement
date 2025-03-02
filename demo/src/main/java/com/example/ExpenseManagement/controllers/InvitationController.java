package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.DTO.InvitationDTO;
import com.example.ExpenseManagement.entities.Invitation;
import com.example.ExpenseManagement.entities.InvitationStatus;
import com.example.ExpenseManagement.exception.InvitationNotFound;
import com.example.ExpenseManagement.services.InvitationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invite")
public class InvitationController {

    private  final Logger logger= LoggerFactory.getLogger(InvitationController.class);

    @Autowired
    private InvitationService invitationService;
    @PostMapping("/send")
    public ResponseEntity<?> sendInvitation(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody InvitationDTO invitationDTO) {
        logger.info("Inside send Invitation controller");
        String token = authorizationHeader.replace("Bearer ", "");
        Invitation response = invitationService.sendInvitation(token,invitationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<?>getAllInvitation() {
        logger.info("Inside Get All Invitation controller");
        List<Invitation> allInvitation = invitationService.getAllInvitation();
        if (allInvitation.isEmpty()) {
            throw new InvitationNotFound("No Invitation Found");
        }
        return new ResponseEntity<>(allInvitation,HttpStatus.OK);
    }



    @GetMapping("/getInvitationByUser")
    public ResponseEntity<?> getSentInvitations(
            @RequestHeader("Authorization") String token) {
        List<InvitationDTO> invitations = invitationService.getInvitationsSentByUser(token);
        if (invitations.isEmpty()){
            throw new InvitationNotFound("Invitations Not Found ");
        }
        return new ResponseEntity<>(invitations,HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingInvitations() {
        List<Invitation> pendingInvitations = invitationService.getPendingInvitations();
        if (pendingInvitations.isEmpty()) {
            throw new InvitationNotFound("No Pending Invitation Found");
        }
        return new ResponseEntity<>(pendingInvitations,HttpStatus.OK);
    }

    @PutMapping("/{invitationId}/status")
    public ResponseEntity<Invitation> updateInvitationStatus(
            @PathVariable String invitationId,
            @RequestParam InvitationStatus status) {
        Invitation updatedInvitation = invitationService.updateInvitationStatus(invitationId, status);
        return ResponseEntity.ok(updatedInvitation);
    }

}

