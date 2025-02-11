package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,String> {

    Optional<Invitation> findByPhoneNumber(String number);
}
