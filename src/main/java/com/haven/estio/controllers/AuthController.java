package com.haven.estio.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haven.estio.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/user/first")
    @Operation(summary = "Get the first name of the current user")
    public ResponseEntity<Map<String, String>> getUserFirstName() throws Exception {
        return ResponseEntity.ok(userService.getUserFirstName());
    }
}
