package com.example.backend.exceptions;

public class InvalidLoginCredentialsException extends RuntimeException {
    public InvalidLoginCredentialsException() {
    }

    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
