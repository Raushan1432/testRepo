package com.theiris.testproject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> handleJwtException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", "Unauthorized");
        response.put("message", "Your token is invalid or has expired. Please log in again.");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
