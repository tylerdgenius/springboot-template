package com.haven.estio.config;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.haven.estio.models.StandardResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        String message = ex.getReason() == null ? "An unexpected error occurred" : ex.getReason();
        return ResponseEntity
                .status(statusCode)
                .body(new StandardResponse<>(statusCode.value(), message, null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardResponse<String>> handleBadRequestException(BadRequestException ex) {
        String message = ex.getMessage() == null ? "Bad request" : ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardResponse<>(HttpStatus.BAD_REQUEST.value(), message, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse<String>> handleGenericException(Exception ex) {
        String message = ex.getMessage() == null ? "An unexpected error occurred" : ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null));
    }
}
