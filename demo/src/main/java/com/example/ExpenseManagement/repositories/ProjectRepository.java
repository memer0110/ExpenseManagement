package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.Invitation;
import com.example.ExpenseManagement.entities.InvitationStatus;
import com.example.ExpenseManagement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {

    @Query("SELECT p FROM Project p WHERE p.user.userId = :userId")
    List<Project> findByUserUserId(String userId);


}
