package com.example.backend.exceptions;

public class CannotBefriendYourselfException extends RuntimeException {

    public CannotBefriendYourselfException() {
    }

    public CannotBefriendYourselfException(String message) {
        super(message);
    }
}
