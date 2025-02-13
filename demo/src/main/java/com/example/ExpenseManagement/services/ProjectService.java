package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.exception.BadRequestException;
import com.example.ExpenseManagement.exception.ResourceNotFoundException;
import com.example.ExpenseManagement.exception.UnauthorizedException;
import com.example.ExpenseManagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveProject(Project project) {
        if (project == null || project.getUser() == null || project.getUser().getUserId() == null) {
            throw new BadRequestException("Project data or user ID cannot be null.");
        }
        return projectRepository.save(project);
    }

    public Project updateProject(String projectId, String userId, Project updatedProject) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectId);

        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();


            if (!existingProject.getUser().getUserId().equals(userId)) {
                throw new UnauthorizedException("You are not authorized to update this project.");
            }

            existingProject.setProjectName(updatedProject.getProjectName());
            existingProject.setProjectDepartment(updatedProject.getProjectDepartment());
            existingProject.setProjectLocation(updatedProject.getProjectLocation());
            existingProject.setProjectStatus(updatedProject.getProjectStatus());
            existingProject.setProjectType(updatedProject.getProjectType());
            existingProject.setProjectedInvestment(updatedProject.getProjectedInvestment());
            existingProject.setExpectedEndDate(updatedProject.getExpectedEndDate());
            existingProject.setExpectedEndDuration(updatedProject.getExpectedEndDuration());
            existingProject.setDeleted(updatedProject.isDeleted());
            existingProject.setUpdated(new java.sql.Timestamp(System.currentTimeMillis()));

            return projectRepository.save(existingProject);
        } else {
            throw new RuntimeException("Project not found");
        }
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> getProjectsByUserId(String userId) {
        List<Project> projects =projectRepository.findByUserUserId(userId);
        if (projects.isEmpty()) {
            throw new ResourceNotFoundException("No projects found for user ID: " + userId);
        }
        return projects;
    }


    public void deleteProject(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with ID " + id + " not found"));

        projectRepository.delete(project);
    }
}
