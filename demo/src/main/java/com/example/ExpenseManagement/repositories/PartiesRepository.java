package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.Parties;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PartiesRepository extends JpaRepository<Parties, String> {
}
