package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.DTO.InvitationDTO;
import com.example.ExpenseManagement.InvitationImpl;
import com.example.ExpenseManagement.customExceptionHandel.InvitationNotFound;
import com.example.ExpenseManagement.customExceptionHandel.UserNotFoundException;
import com.example.ExpenseManagement.entities.Invitation;
import com.example.ExpenseManagement.entities.InvitationStatus;
import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.InvitationRepository;
import com.example.ExpenseManagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvitationService implements InvitationImpl {
     private  final Logger logger= LoggerFactory.getLogger(InvitationService.class);

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;
    @Override
    public Invitation sendInvitation(String token, InvitationDTO invitationDTO) {
        logger.info("Inside Send Invitation");
        //for checking contact number is exist or not
        String number=invitationDTO.getContactNumber();
        String userId = jwtService.extractUserId(token);
        User invitedBy = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
       User invitedUser = userRepository.findByPhoneNo(number);
        if (invitedUser==null) {
            throw new UserNotFoundException("User not registered in the system");
        }
        //find project is exist or not
        Project project = projectRepository.findById(invitationDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        logger.info("Find project exist or not");
        //check alread exist or Not
        Optional<Invitation> existingInvitation = invitationRepository.findByPhoneNumber(number);
        if (existingInvitation.isPresent()) {
            throw new InvitationNotFound("Invitation already sent!");
        }

        Invitation invitation = new Invitation();
        invitation.setUserName(invitationDTO.getName());
        invitation.setPhoneNumber(invitationDTO.getContactNumber());
        invitation.setProjectedBudget(invitationDTO.getProjectedAmount());
        invitation.setProject(project);
        invitation.setInvitationId(String.valueOf(invitedBy));
        invitation.setStatus(InvitationStatus.PENDING);
        return invitationRepository.save(invitation);

    }

    @Override
    public List<Invitation> getAllInvitation() {
        logger.info("Inside Get All Invitations");
        return invitationRepository.findAll();
    }

    public List<InvitationDTO> getInvitationsSentByUser(String token) {
        // Extract userId from the token
        String userId = jwtService.extractUserId(token);

        // Fetch invitations sent by this user
        List<Invitation> invitations = invitationRepository.findBySentBy(userId);

        // Convert Invitation entities to DTOs
        return invitations.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    private InvitationDTO mapToDTO(Invitation invitation) {
        return new InvitationDTO(
                invitation.getUserName(),
                invitation.getProject().getProjectId(),
                invitation.getPhoneNumber(),
                invitation.getProjectedBudget()
        );
    }

}
