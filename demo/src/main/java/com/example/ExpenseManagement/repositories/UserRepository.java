package com.example.ExpenseManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ExpenseManagement.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT * FROM users WHERE phone_no = :phoneNumber", nativeQuery = true)
	User findByPhoneNo(String phoneNumber);

	Optional<User> findByUserId(String UserId);
	

}
