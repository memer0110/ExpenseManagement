package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.DTO.ProjectMemberDTO;
import com.example.ExpenseManagement.entities.ProjectMember;
import com.example.ExpenseManagement.services.ProjectMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projectMembers")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<ProjectMemberDTO> createProjectMember(@RequestBody ProjectMemberDTO projectMemberDTO) {
        ProjectMember savedProjectMember = projectMemberService.saveProjectMember(projectMemberDTO);
        ProjectMemberDTO resProjctMemberDTO=new ProjectMemberDTO();
        BeanUtils.copyProperties(savedProjectMember,resProjctMemberDTO);
        return ResponseEntity.ok(resProjctMemberDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectMember>> getAllProjectMembers() {
        return ResponseEntity.ok(projectMemberService.getAllProjectMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectMember> getProjectMemberById(@PathVariable String id) {
        Optional<ProjectMember> projectMember = projectMemberService.getProjectMemberById(id);
        return projectMember.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectMember> updateProjectMember(
            @PathVariable String id,
            @RequestBody ProjectMember projectMemberDetails) {
        Optional<ProjectMember> updatedProjectMember = projectMemberService.updateProjectMember(id, projectMemberDetails);
        return updatedProjectMember.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable String id) {
        boolean deleted = projectMemberService.deleteProjectMember(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
