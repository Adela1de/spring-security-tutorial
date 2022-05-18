package com.luizaugusto.springsecuritytutorial.controllers.exception;

import com.luizaugusto.springsecuritytutorial.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<StandardError> unexpectedException(UserNotFound e, ServletRequest request)
    {
        var error = new StandardError(e.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> unexpectedException(NullPointerException e, ServletRequest request)
    {
        var error = new StandardError(
                "Something went wrong!",
                HttpStatus.BAD_REQUEST.value(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}