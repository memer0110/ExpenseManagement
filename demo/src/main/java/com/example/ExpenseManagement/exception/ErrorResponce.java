package com.example.ExpenseManagement.exception;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponce {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorResponce(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
