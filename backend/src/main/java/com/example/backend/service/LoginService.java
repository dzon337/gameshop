package com.example.backend.service;

import com.example.backend.model.request.LoginRequest;
import com.example.backend.model.request.SearchRequest;
import com.example.backend.model.user.User;
import com.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private IUserRepository userRepository;

    public String login(LoginRequest request) {
        var userOptional = userRepository.findUserByUsername(request.getUsername());
        System.out.println("USERNAME"+ userOptional);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(request.getPassword())) {
                return "Login successful";
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public String search(SearchRequest request) {
        var userOptional = userRepository.findUserByUsername(request.getUsername());
        System.out.println("USERNAME"+ userOptional);
        if (userOptional.isPresent()) {
            return "User is found";
        } else {
            throw new RuntimeException("User not found");
        }
    }
}