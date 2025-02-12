package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.DTO.InvitationDTO;
import com.example.ExpenseManagement.InvitationImpl;
import com.example.ExpenseManagement.customExceptionHandel.UserNotFoundException;
import com.example.ExpenseManagement.entities.Invitation;
import com.example.ExpenseManagement.entities.InvitationStatus;
import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.InvitationRepository;
import com.example.ExpenseManagement.repositories.ProjectRepository;
import com.example.ExpenseManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvitationService implements InvitationImpl {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;
    @Override
    public String sendInvitation(String token, InvitationDTO invitationDTO) {
        //for checking contact number is exist or not
        String number=invitationDTO.getContactNumber();
        String userId = jwtService.extractUserId(token);
        User invitedBy = userRepository.findById(userId)

                .orElseThrow(() -> new UserNotFoundException("User not found"));
       Optional<User> invitedUser = userRepository.findByPhoneNo(number);
        if (invitedUser.isEmpty()) {
            throw new UserNotFoundException("User not registered in the system");
        }
//find project is exist or not
        Project project = projectRepository.findById(invitationDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));


        Optional<Invitation> existingInvitation = invitationRepository.findByPhoneNumber(number);
        if (existingInvitation.isPresent()) {
            return "Invitation already sent!";
        }

        Invitation invitation = new Invitation();
        invitation.setUserName(invitationDTO.getName());
        invitation.setPhoneNumber(invitationDTO.getContactNumber());
        invitation.setProjectedBudget(invitationDTO.getProjectedAmount());
        invitation.setProject(project);
        invitation.setInvitationId(String.valueOf(invitedBy));
        invitation.setStatus(InvitationStatus.PENDING);
        invitationRepository.save(invitation);
        return "Invitation sent successfully!";
    }
}
