package com.blog_app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice  
public class GlobalExceptionHandler {

    // Handle cases where a requested blog is not found
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Blog not found.");
    }

    // Handle invalid input or bad requests
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
    }

    // Handle all other exceptions (fallback)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong! Please try again later.");
    }
}

