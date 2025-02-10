package com.example.ExpenseManagement.customExceptionHandel;

public class InvitationNotFound extends RuntimeException{

    public InvitationNotFound(String message) {
        super(message);
    }
}
