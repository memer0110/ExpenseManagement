package com.example.ExpenseManagement.DTO;

import com.example.ExpenseManagement.DTO.ProjectDTO;
import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.entities.User;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getProjectId(),
                project.getProjectName(),
                project.getProjectDepartment(),
                project.getProjectLocation(),
                project.getProjectStatus(),
                project.getProjectType(),
                project.getProjectedInvestment(),
                project.getExpectedEndDate(),
                project.getExpectedEndDuration(),
                project.isDeleted(),
                project.getStartDate(),
                project.getUser().getUserId()
        );
    }

    public static Project toEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDepartment(projectDTO.getProjectDepartment());
        project.setProjectLocation(projectDTO.getProjectLocation());
        project.setProjectStatus(projectDTO.getProjectStatus());
        project.setProjectType(projectDTO.getProjectType());
        project.setProjectedInvestment(projectDTO.getProjectedInvestment());
        project.setExpectedEndDate(projectDTO.getExpectedEndDate());
        project.setExpectedEndDuration(projectDTO.getExpectedEndDuration());
        project.setDeleted(projectDTO.isDeleted());
        project.setStartDate(projectDTO.getStartDate());
        User user = new User();
        user.setUserId(projectDTO.getUserId());  // Assuming user ID is valid
        project.setUser(user);
        return project;
    }
}
