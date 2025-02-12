package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepos extends JpaRepository<ProjectMember,String> {
}
