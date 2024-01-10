package com.example.backend.exceptions;

public class GameReviewNotFoundException extends RuntimeException {
    public GameReviewNotFoundException() {
    }

    public GameReviewNotFoundException(String message) {
        super(message);
    }
}
