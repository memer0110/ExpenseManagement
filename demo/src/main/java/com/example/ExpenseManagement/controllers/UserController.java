package com.example.ExpenseManagement.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExpenseManagement.DTO.AuthResponseDTO;
import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
    	if (userService.isUserExist(user.getUserId())) {
            return ResponseEntity.status(409).build();  // Conflict error if user exists
        }
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        User registeredUser = userService.saveUser(user);
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        AuthResponseDTO tokens = userService.authenticateUser(user);
        if (tokens == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok(tokens);
    }
    
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }
}
