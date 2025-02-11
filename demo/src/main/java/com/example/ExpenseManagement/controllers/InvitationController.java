package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.DTO.InvitationDTO;
import com.example.ExpenseManagement.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invite")
public class InvitationController {
@Autowired
    private InvitationService invitationService;
    @PostMapping("/send")
    public ResponseEntity<String> sendInvitation(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody InvitationDTO invitationDTO) {
        String token = authorizationHeader.replace("Bearer ", "");
        String response = invitationService.sendInvitation(token,invitationDTO);
        return ResponseEntity.ok(response);
    }
}

}
