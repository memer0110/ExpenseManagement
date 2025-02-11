package com.example.ExpenseManagement.customExceptionHandel;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
