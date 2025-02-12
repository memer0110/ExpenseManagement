package com.example.ExpenseManagement.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ExpenseManagement.DTO.AuthResponseDTO;
import com.example.ExpenseManagement.DTO.UserDTO;
import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {

        user.setUserPassword(encoder.encode(user.getUserPassword()));
        User savedUser = userRepository.save(user);


        return savedUser;
    }

    public boolean isUserExist(String phoneNo) {

        boolean exists = userRepository.findByPhoneNo(phoneNo) != null;

        return exists;
    }

    public AuthResponseDTO authenticateUser(User user) {
        String credential = user.getPhoneNo();


        User fullUser = userRepository.findByPhoneNo(credential);
        if (fullUser == null) {

            throw new RuntimeException("User not found");
        }

        String userId = fullUser.getUserId();


        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId, user.getUserPassword())
        );

        if (authentication.isAuthenticated()) {


            String accessToken = jwtService.generateAccessToken(fullUser);
            String refreshToken = jwtService.generateRefreshToken(fullUser);

            UserDTO userDTO = new UserDTO(
                    fullUser.getUserId(),
                    fullUser.getCountryCode(),
                    fullUser.getCreated(),
                    fullUser.getEmail(),
                    fullUser.getFirstName(),
                    fullUser.getLastName(),
                    fullUser.getGender(),
                    fullUser.getImgUrl(),
                    fullUser.isDeleted(),
                    fullUser.getPhoneNo(),
                    fullUser.getRole(),
                    fullUser.getUpdated(),
                    fullUser.isUserStatus()
            );


            return new AuthResponseDTO(accessToken, refreshToken, userDTO);
        }


        throw new RuntimeException("Login Failed");
    }

    public User updateUser(String userId, User updatedUser) {


        Optional<User> existingUserOpt = userRepository.findById(userId);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update only non-null values
            if (updatedUser.getFirstName() != null) existingUser.setFirstName(updatedUser.getFirstName());
            if (updatedUser.getLastName() != null) existingUser.setLastName(updatedUser.getLastName());
            if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getCountryCode() != null) existingUser.setCountryCode(updatedUser.getCountryCode());
            if (updatedUser.getGender() != null) existingUser.setGender(updatedUser.getGender());
            if (updatedUser.getImgUrl() != null) existingUser.setImgUrl(updatedUser.getImgUrl());
            if (updatedUser.getPhoneNo() != null) existingUser.setPhoneNo(updatedUser.getPhoneNo());
            if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());
            if (updatedUser.getToken() != null) existingUser.setToken(updatedUser.getToken());
            if (updatedUser.getUserPassword() != null) existingUser.setUserPassword(encoder.encode(updatedUser.getUserPassword()));

            // Only update boolean values if they are explicitly provided in the request
            if (updatedUser.isDeleted() != null) existingUser.setDeleted(updatedUser.isDeleted());
            if (updatedUser.isUserStatus() != null) existingUser.setUserStatus(updatedUser.isUserStatus());

            User updated = userRepository.save(existingUser);

            return updated;
        } else {

            throw new RuntimeException("User with ID " + userId + " not found.");
        }
    }
}
