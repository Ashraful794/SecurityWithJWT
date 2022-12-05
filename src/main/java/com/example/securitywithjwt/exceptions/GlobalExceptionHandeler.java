package com.example.securitywithjwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {

    @ExceptionHandler(UsernameNotFoundException.class)

    public ResponseEntity resourceNotFoundException(UsernameNotFoundException ex)
    {
        String message=ex.getMessage();
        return new ResponseEntity(message,HttpStatus.NOT_FOUND);
    }


}
