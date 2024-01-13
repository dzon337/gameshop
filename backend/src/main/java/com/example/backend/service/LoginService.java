package com.example.backend.service;

import java.util.Optional;

import com.example.backend.model.user.User;
import com.example.backend.repository.IUserRepository;
import com.example.backend.model.request.LoginRequest;
import com.example.backend.model.request.SearchRequest;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.exceptions.UserNotRegisteredException;
import com.example.backend.exceptions.InvalidLoginCredentialsException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginService {

    @Autowired
    private IUserRepository userRepository;

    public String login(final LoginRequest request) {
        final Optional<User> possibleUser = this.userRepository.findUserByUsername(request.getUsername());

        if (possibleUser.isPresent()) {
            final User user = possibleUser.get();

            if (user.getPassword().equals(request.getPassword())) {
                return "Login successful";
            }
            else {
                throw new InvalidLoginCredentialsException("Invalid password!");
            }
        }
        else {
            throw new UserNotRegisteredException("User not found!");
        }
    }

    public String search(final SearchRequest request) {
        final Optional<User> possibleUser = this.userRepository.findUserByUsername(request.getUsername());

        if (possibleUser.isPresent()) {
            return "User is found";
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

}