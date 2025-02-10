package com.example.ExpenseManagement.customExceptionHandel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvitationNotFound.class)
    public ResponseEntity<?>InvitationNotFoundException(InvitationNotFound exception) {
     ErrorResponce errorResponce=new ErrorResponce(LocalDateTime.now(),exception.getMessage(),"SomeThing Went Wrong");
     return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?>userNotFoundException(UserNotFoundException exception) {
        ErrorResponce errorResponce=new ErrorResponce(LocalDateTime.now(),exception.getMessage(),"SomeThing Went Wrong");
        return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
    }

}
