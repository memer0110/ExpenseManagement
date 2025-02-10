package com.example.ExpenseManagement.services;


import com.example.ExpenseManagement.entities.UserPrinciple;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ExpenseManagement.entities.User;
import com.example.ExpenseManagement.repositories.UserRepository;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User users = userRepo.findByPhoneNo(identifier);
                /*.orElseThrow(() -> new UsernameNotFoundException("User not found with identifier: " + identifier));*/

        if (users==null){
            throw new UsernameNotFoundException("User not Found");
        }
        return new UserPrinciple(users);
    }

}
