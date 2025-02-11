package com.example.ExpenseManagement.customExceptionHandel;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
