package com.infy.infyinterns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class InfyInternException extends Exception {
    public InfyInternException(String message) {
        super(message);
    }

}
