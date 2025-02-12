package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.DTO.InvitationDTO;
import com.example.ExpenseManagement.customExceptionHandel.InvitationNotFound;
import com.example.ExpenseManagement.entities.Invitation;
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
    public ResponseEntity<?>getAllInvitation()
    {
        logger.info("Inside Get All Invitation controller");
        List<Invitation> allInvitation = invitationService.getAllInvitation();
        if (allInvitation.isEmpty()) {
            throw new InvitationNotFound("No Invitation Found");
        }
        return new ResponseEntity<>(allInvitation,HttpStatus.OK);
    }


