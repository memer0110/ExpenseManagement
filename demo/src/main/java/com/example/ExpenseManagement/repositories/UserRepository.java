package com.example.ExpenseManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ExpenseManagement.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByPhoneNo(String phoneNo);

	Optional<User> findByUserId(String UserId);
	
	boolean existsUserByUserId(String userId);
}
