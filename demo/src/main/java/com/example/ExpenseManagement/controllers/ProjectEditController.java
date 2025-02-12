package com.example.ExpenseManagement.controllers;



import com.example.ExpenseManagement.entities.ProjectEdit;
import com.example.ExpenseManagement.services.ProjectEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/projectedits")
public class ProjectEditController {

    @Autowired
    private ProjectEditService projectEditService;

    // Create Project Edit
    @PostMapping
    public ProjectEdit createProjectEdit(@RequestBody ProjectEdit projectEdit) {
        return projectEditService.createProjectEdit(projectEdit);
    }

    // Edit project location
    @PatchMapping("/{id}/location")
    public ProjectEdit editProjectLocation(@PathVariable String id, @RequestBody String newLocation) {
        return projectEditService.editProjectLocation(id, newLocation);
    }

//    // Edit project department
//    @PatchMapping("/{id}/department")
//    public ProjectEdit editProjectDepartment(@PathVariable String id, @RequestBody String newDepartment) {
//        return  projectEditService.editProjectDepartment(id, newDepartment);
//    }

    // Edit project name
    @PatchMapping("/{id}/name")
    public ProjectEdit editProjectName(@PathVariable String id, @RequestBody String newName) {
        return projectEditService.editProjectName(id, newName);
    }

    // Edit project type
    @PatchMapping("/{id}/type")
    public ProjectEdit editProjectType(@PathVariable String id, @RequestBody String newType) {
        return projectEditService.editProjectType(id, newType);
    }

    // Edit project status
    @PatchMapping("/{id}/status")
    public ProjectEdit editProjectStatus(@PathVariable String id, @RequestBody String newStatus) {
        return projectEditService.editProjectStatus(id, newStatus);
    }


//
}


