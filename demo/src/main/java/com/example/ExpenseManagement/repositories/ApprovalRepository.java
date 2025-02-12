package com.example.ExpenseManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ExpenseManagement.entities.Approval;
import com.example.ExpenseManagement.entities.User;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, String> {
    
    List<Approval> findByUserAndApprovalStatus(User user, String status);
}

