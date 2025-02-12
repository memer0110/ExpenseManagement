package com.example.ExpenseManagement.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;


@Service
public class MyUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        logger.info("Attempting to load user by identifier: {}", identifier);

        User user = userRepo.findByUserId(identifier)
                .orElseThrow(() -> {
                    logger.warn("User not found with identifier: {}", identifier);
                    return new UsernameNotFoundException("User not found with identifier: " + identifier);
                });

        logger.info("User found: {} | Role: {}", user.getUserId(), user.getRole());

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getUserPassword(),
                authorities
        );

    }
}
