package com.example.backend.service;

import com.example.backend.model.request.RegisterRequest;
import com.example.backend.model.user.User;
import com.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private IUserRepository userRepository;


    public User register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .build();
        return  userRepository.save(user);

    }
}
