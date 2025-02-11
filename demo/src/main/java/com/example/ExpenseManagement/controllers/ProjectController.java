package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.DTO.ProjectDTO;
import com.example.ExpenseManagement.DTO.ProjectMapper;
import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.services.JWTService;
import com.example.ExpenseManagement.services.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    JWTService jwtService;


    @PostMapping
    public ResponseEntity<ProjectDTO> createOrUpdateProject(@RequestBody ProjectDTO projectdto) {
        Project project=ProjectMapper.toEntity(projectdto);

        Project savedProject = projectService.saveProject(project);
        return ResponseEntity.ok(ProjectMapper.toDTO(savedProject));
    }

    @GetMapping("/user")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(HttpServletRequest request) {

        String token = extractToken(request);

        String userId=jwtService.extractUserId(token);

        List<Project> projects = projectService.getProjectsByUserId(userId);
        List<ProjectDTO> projectDTOs = projects.stream().map(ProjectMapper::toDTO).toList();
        return ResponseEntity.ok(projectDTOs);
    }


//    @GetMapping
//    public ResponseEntity<List<Project>> getAllProjects() {
//        return ResponseEntity.ok(projectService.getAllProjects());
//    }


    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        return project != null ? ResponseEntity.ok(ProjectMapper.toDTO(project)) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable String projectId,
            @RequestBody Project updatedProject,
            HttpServletRequest request) {


        String token = extractToken(request);
        String userId = jwtService.extractUserId(token);


        Project updated = projectService.updateProject(projectId, userId, updatedProject);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Project deleted successfully.");
        return ResponseEntity.ok(response);
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
