package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.entities.ProjectEdit;
import com.example.ExpenseManagement.services.ProjectEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/projectedits")
public class ProjectEditController {

    @Autowired
    private ProjectEditService projectEditService;

    // Get project edit by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectEdit> getProjectEditById(@PathVariable String id) {
        return projectEditService.getProjectEditById(id)
                .map(projectEdit -> ResponseEntity.ok().body(projectEdit))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Edit project details
    @PutMapping("/{id}")
    public ResponseEntity<ProjectEdit> editProjectEdit(@PathVariable String id, @RequestBody ProjectEdit updatedProjectEdit) {
        ProjectEdit updated = projectEditService.updateProjectEdit(id, updatedProjectEdit);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
