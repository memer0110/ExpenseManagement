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
import com.example.ExpenseManagement.repositories.ProjectRepository;
import com.example.ExpenseManagement.repositories.UserRepository;

import org.hibernate.annotations.CreationTimestamp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Invitation sendInvitation(String token, InvitationDTO invitationDTO) {

        //for checking contact number is exist or not
        String number=invitationDTO.getContactNumber();
        String userId = jwtService.extractUserId(token);
        logger.info("User Id :-"+userId);

        User invitedBy = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this Id"));
       User invitedUser = userRepository.findByPhoneNo(number);
        if (invitedUser==null) {
            throw new UserNotFoundException("User not registered in the system");
        }
        //find project is exist or not
        Project project = projectRepository.findById(invitationDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

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
        invitation.setUser(invitedBy);
        invitation.setStatus(InvitationStatus.PENDING);
        return invitationRepository.save(invitation);
    }

    @Override
    public List<Invitation> getAllInvitation() {

        logger.info("Inside Get All Invitations");
        List<Invitation> all = invitationRepository.findAll();
        if (all.isEmpty())
        {
            throw new InvitationNotFound("No invitation found");
        }
        return all;


        return invitationRepository.findAll();

    }

    public List<InvitationDTO> getInvitationsSentByUser(String token) {
        String token1=token;
        if (token1 != null && token1.startsWith("Bearer ")) {
            token1 = token1.substring(7).trim();
        }
        String userId = jwtService.extractUserId(token1);
        logger.info("Find All Invitations Send By Self::-"+userId);
        List<Invitation> invitations = invitationRepository.findByUser(userId);
        return invitations.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private InvitationDTO mapToDTO(Invitation invitation) {
        return new InvitationDTO(
                invitation.getUserName(),
                invitation.getCountryCode(),
                invitation.getProject().getProjectId(),
                invitation.getPhoneNumber(),
                invitation.getProjectedBudget(),
                invitation.getStatus()
        );
    }
    

    public List<Invitation> getPendingInvitations() {
        logger.info("Get All Pending Invitation");
        return invitationRepository.findByStatus(InvitationStatus.PENDING);
    }
   /* public List<Invitation> getAcceptedInvitations() {
        logger.info("Get All Pending Invitation");
        return invitationRepository.findByStatus(InvitationStatus.ACCEPTED);
    }*/


    public Invitation updateInvitationStatus(String invitationId, InvitationStatus status) {
        Optional<Invitation> invitationID = invitationRepository.findById(invitationId);
        if (invitationID.isPresent()) {
            Invitation invitation = invitationID.get();
            invitation.setStatus(status);
            return invitationRepository.save(invitation);
        } else {
            throw new InvitationNotFound("Invitation not found for ID: " + invitationId);
        }
    }

}
