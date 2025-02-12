package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.DTO.ProjectMemberDTO;
import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.entities.ProjectMember;
import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.ProjectMemberRepos;
import com.example.ExpenseManagement.repositories.ProjectRepository;
import com.example.ExpenseManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectMemberService {

    @Autowired
    private ProjectMemberRepos projectMemberRepos;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectMember saveProjectMember(ProjectMemberDTO projectMemberDTO) {
        ProjectMember projectMember = new ProjectMember();

        projectMember.setProjectMemberId(projectMemberDTO.getProjectMemberId());
        projectMember.setCreated(projectMemberDTO.getCreated());
        projectMember.setProjectMemberActualShares(projectMemberDTO.getProjectMemberActualShares());
        projectMember.setProjectMemberInvestment(projectMemberDTO.getProjectMemberInvestment());
        projectMember.setProjectMemberProfitShares(projectMemberDTO.getProjectMemberProfitShares());
        projectMember.setProjectMemberstatus(projectMemberDTO.getProjectMemberstatus());
        projectMember.setProjectMemberType(projectMemberDTO.getProjectMemberType());
        projectMember.setUpdated(projectMemberDTO.getUpdated());

        // Fetch the existing Project by ID
        Project project = projectRepository.findById(projectMemberDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectMember.setProject(project);

        // Fetch the existing User by ID
        User user = userRepository.findById(projectMemberDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        projectMember.setUser(user);

        //projectMemberRepos.save(projectMember);
        return ResponseEntity.ok(projectMemberRepos.save(projectMember)).getBody();
    }

    public List<ProjectMember> getAllProjectMembers() {
        return projectMemberRepos.findAll();
    }

    public Optional<ProjectMember> getProjectMemberById(String id) {
        return projectMemberRepos.findById(id);
    }

    public Optional<ProjectMember> updateProjectMember(String id, ProjectMember projectMemberDetails) {
        return projectMemberRepos.findById(id).map(projectMember -> {
            projectMember.setProjectMemberActualShares(projectMemberDetails.getProjectMemberActualShares());
            projectMember.setProjectMemberInvestment(projectMemberDetails.getProjectMemberInvestment());
            projectMember.setProjectMemberProfitShares(projectMemberDetails.getProjectMemberProfitShares());
            projectMember.setProjectMemberstatus(projectMemberDetails.getProjectMemberstatus());
            projectMember.setProjectMemberType(projectMemberDetails.getProjectMemberType());
            projectMember.setUpdated(projectMemberDetails.getUpdated());
            return projectMemberRepos.save(projectMember);
        });
    }

    // Delete project member
    public boolean deleteProjectMember(String id) {
        if (projectMemberRepos.existsById(id)) {
            ProjectMember projectMember=projectMemberRepos.getReferenceById(id);
            projectMember.setProjectMemberstatus("Not Active");
            projectMemberRepos.save(projectMember);
            return true;
        }
        return false;
    }
}



