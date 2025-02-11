package com.example.ExpenseManagement.customExceptionHandel;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
