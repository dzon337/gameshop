package com.example.backend.service;

import java.util.Optional;

import com.example.backend.model.user.User;
import com.example.backend.repository.IUserRepository;
import com.example.backend.model.request.RegisterRequest;
import com.example.backend.exceptions.UserAlreadyRegisteredException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RegisterService {

    @Autowired
    private IUserRepository userRepository;

    public User register(final RegisterRequest request) {
        final Optional<User> possibleUser =
                this.userRepository.findUserByUsernameAndEmail(request.getUsername(), request.getEmail());

        possibleUser.ifPresent(user -> {
            throw new UserAlreadyRegisteredException(user.getFirstname() + " " + user.getEmail() + " already registered!");
        });

        final User user = User.builder()
                                    .firstname(request.getFirstname())
                                    .lastname(request.getLastname())
                                    .email(request.getEmail())
                                    .password(request.getPassword())
                                    .username(request.getUsername())
                                    .build();

        userRepository.save(user);
        return user;
    }

}