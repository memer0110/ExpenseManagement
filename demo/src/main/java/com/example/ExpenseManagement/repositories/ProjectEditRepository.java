package com.example.ExpenseManagement.repositories;

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
//    @Modifying
//    @Transactional
//    @Query("UPDATE ProjectEdit p SET p.department = :department WHERE p.id = :id")
//    void EditedProjectDepartment(@Param("id") String id, @Param("department") String department);
}

