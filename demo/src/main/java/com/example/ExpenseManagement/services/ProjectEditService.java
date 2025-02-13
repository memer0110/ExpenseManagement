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

    public Optional<ProjectEdit> getProjectEditById(String id) {
        return projectEditRepository.findById(id);
    }

    public ProjectEdit updateProjectEdit(String id, ProjectEdit updatedProjectEdit) {
        Optional<ProjectEdit> existingProjectEdit = projectEditRepository.findById(id);
        if (existingProjectEdit.isPresent()) {
            ProjectEdit projectEdit = existingProjectEdit.get();
            projectEdit.setEditedProjectName(updatedProjectEdit.getEditedProjectName());
            projectEdit.setEditedProjectLocation(updatedProjectEdit.getEditedProjectLocation());
            projectEdit.setEditedProjectStatus(updatedProjectEdit.getEditedProjectStatus());
            projectEdit.setEditedProjectedInvestment(updatedProjectEdit.getEditedProjectedInvestment());
            projectEdit.setEditedStartDate(updatedProjectEdit.getEditedStartDate());
            projectEdit.setEditedExpectedEndDate(updatedProjectEdit.getEditedExpectedEndDate());
            projectEdit.setEditedExpectedEndDuration(updatedProjectEdit.getEditedExpectedEndDuration());

            // Update other fields as necessary

            return projectEditRepository.save(projectEdit);
        } else {
            return null; // Or throw an exception if project edit not found
        }
    }
}
