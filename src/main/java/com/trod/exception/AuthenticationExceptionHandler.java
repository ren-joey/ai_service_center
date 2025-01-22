package com.trod.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler({
            AuthenticationCredentialsNotFoundException.class,
            InsufficientAuthenticationException.class
    })
    public ResponseEntity<Map<String, String>> handleAuthenticationExceptions(
            Exception ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }
}
