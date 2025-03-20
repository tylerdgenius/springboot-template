package com.haven.estio.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haven.estio.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public Map<String, String> getUserFirstName() {
        return Map.of("firstName", "John");
    }
}
