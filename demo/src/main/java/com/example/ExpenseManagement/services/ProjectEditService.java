package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.entities.ProjectEdit;
import com.example.ExpenseManagement.repositories.ProjectEditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProjectEditService {

    @Autowired
    private ProjectEditRepository projectEditRepository;

    // Create a new Project Edit
    public ProjectEdit saveProjectEdit(ProjectEdit projectEdit) {
        return projectEditRepository.save(projectEdit);
    }

    // Update existing Project Edit with multiple fields
    public Optional<ProjectEdit> updateProjectEdit(String projectEditsId, double editedProjectedInvestment,
                                                   LocalDateTime editedStartDate, LocalDateTime editedExpectedEndDate,
                                                   String editedProjectName, String editedProjectLocation,
                                                   String editedProjectStatus, String editedProjectType) {
        Optional<ProjectEdit> projectEditOpt = projectEditRepository.findById(projectEditsId);
        if (projectEditOpt.isPresent()) {
            ProjectEdit projectEdit = projectEditOpt.get();
            projectEdit.setEditedProjectedInvestment(editedProjectedInvestment);
            projectEdit.setEditedStartDate(editedStartDate);
            projectEdit.setEditedExpectedEndDate(editedExpectedEndDate);
            projectEdit.setEditedProjectName(editedProjectName);
            projectEdit.setEditedProjectLocation(editedProjectLocation);
            projectEdit.setEditedProjectStatus(editedProjectStatus);
            projectEdit.setEditedProjectType(editedProjectType);
            return Optional.of(projectEditRepository.save(projectEdit));
        }
        return Optional.empty();
    }

    // Retrieve a Project Edit by ID
    public Optional<ProjectEdit> getProjectEditById(String projectEditsId) {
        return projectEditRepository.findById(projectEditsId);
    }
}
