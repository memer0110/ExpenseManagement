package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.entities.ProjectEdit;
import com.example.ExpenseManagement.repositories.ProjectEditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectEditService {

    @Autowired
    private ProjectEditRepository projectEditRepository;

    // This method will allow you to save a new ProjectEdit entity
    public ProjectEdit createProjectEdit(ProjectEdit projectEdit) {
        return projectEditRepository.save(projectEdit);
    }

    // Edit project name
    public ProjectEdit editProjectName(String projectEditsId, String newName) {
        ProjectEdit projectEdit = projectEditRepository.findById(projectEditsId)
                .orElseThrow(() -> new RuntimeException("ProjectEdit not found"));
        projectEdit.setEditedProjectName(newName);
        return  projectEditRepository.save(projectEdit);
    }

    // Edit project type
    public ProjectEdit editProjectType(String projectEditsId, String newType) {
        ProjectEdit projectEdit = (ProjectEdit) projectEditRepository.findById(projectEditsId)
                .orElseThrow(() -> new RuntimeException("ProjectEdit not found"));
        projectEdit.setEditedProjectType(newType);
        return projectEditRepository.save(projectEdit);
    }



    // Edit project location
    public ProjectEdit editProjectLocation(String projectEditsId, String newLocation) {
        ProjectEdit projectEdit = projectEditRepository.findById(projectEditsId)
                .orElseThrow(() -> new RuntimeException("ProjectEdit not found"));
        projectEdit.setEditedProjectLocation(newLocation);
        return projectEditRepository.save(projectEdit);
    }

//    public ProjectEdit editProjectDepartment(String projectEditsId, String newDepartment) {
//        projectEditRepository.EditedProjectDepartment(newDepartment, projectEditsId);
//
//        // Return the updated project edit
//        return projectEditRepository.findById(projectEditsId)
//                .orElseThrow(() -> new RuntimeException("ProjectEdit not found"));
//    }


    // Edit project status
    public ProjectEdit editProjectStatus(String projectEditsId, String newStatus) {
        ProjectEdit projectEdit = (ProjectEdit) projectEditRepository.findById(projectEditsId)
                .orElseThrow(() -> new RuntimeException("ProjectEdit not found"));
        projectEdit.setEditedProjectStatus(newStatus);
        return projectEditRepository.save(projectEdit);
    }


//    public ProjectEdit getAllProjectEdits(ProjectEdit projectEdit) {
//        return projectEditRepository.save(projectEdit);
//
//    }
}
