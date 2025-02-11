package com.example.ExpenseManagement.repositories;

import com.example.ExpenseManagement.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,String> {

    Optional<Invitation> findByPhoneNumber(String number);
    @Query(value = "SELECT * FROM invitation WHERE invitation_id = :userId", nativeQuery = true)
    List<Invitation> findByUser(String userId);
}
