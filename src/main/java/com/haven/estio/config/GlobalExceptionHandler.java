package com.haven.estio.config;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.haven.estio.models.StandardResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new StandardResponse<>(false, ex.getReason(), null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardResponse<String>> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardResponse<>(false, "Resource not found", null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse<String>> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StandardResponse<>(false, "An unexpected error occurred", null));
    }
}
