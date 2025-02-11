package com.example.ExpenseManagement.customExceptionHandel;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
