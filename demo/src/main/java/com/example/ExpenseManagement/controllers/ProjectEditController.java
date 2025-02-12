package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.entities.ProjectEdit;
import com.example.ExpenseManagement.services.ProjectEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/project-edits")
public class ProjectEditController {

    @Autowired
    private ProjectEditService projectEditService;

    // Create a new Project Edit
    @PostMapping
    public ResponseEntity<ProjectEdit> createProjectEdit(@RequestBody ProjectEdit projectEdit) {
        ProjectEdit savedProjectEdit = projectEditService.saveProjectEdit(projectEdit);
        return ResponseEntity.ok(savedProjectEdit);
    }

    // Update a Project Edit with multiple fields
    @PutMapping("/{id}")
    public ResponseEntity<ProjectEdit> updateProjectEdit(
            @PathVariable("id") String projectEditsId,
            @RequestParam double editedProjectedInvestment,
            @RequestParam LocalDateTime editedStartDate,
            @RequestParam LocalDateTime editedExpectedEndDate,
            @RequestParam String editedProjectName,
            @RequestParam String editedProjectLocation,
            @RequestParam String editedProjectStatus,
            @RequestParam String editedProjectType) {

        Optional<ProjectEdit> updatedProjectEdit = projectEditService.updateProjectEdit(
                projectEditsId, editedProjectedInvestment, editedStartDate, editedExpectedEndDate,
                editedProjectName, editedProjectLocation, editedProjectStatus, editedProjectType);

        return updatedProjectEdit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get a Project Edit by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectEdit> getProjectEdit(@PathVariable("id") String projectEditsId) {
        Optional<ProjectEdit> projectEdit = projectEditService.getProjectEditById(projectEditsId);
        return projectEdit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
