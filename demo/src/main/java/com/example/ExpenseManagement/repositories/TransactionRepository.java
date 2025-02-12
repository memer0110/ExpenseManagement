package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    List<Transaction> findByProject_ProjectId(String projectId);
}
