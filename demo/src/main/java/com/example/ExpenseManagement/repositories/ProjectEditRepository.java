package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.Project;
import com.example.ExpenseManagement.entities.ProjectEdit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectEditRepository extends JpaRepository<ProjectEdit, String> {

    @Query("SELECT p FROM Project p WHERE p.user.userId = :userId")
    List<ProjectEdit> findByUserUserId(String userId);

}
