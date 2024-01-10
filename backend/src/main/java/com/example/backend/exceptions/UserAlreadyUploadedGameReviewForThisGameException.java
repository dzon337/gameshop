package com.example.backend.exceptions;

public class UserAlreadyUploadedGameReviewForThisGameException extends RuntimeException {

    public UserAlreadyUploadedGameReviewForThisGameException() {
    }

    public UserAlreadyUploadedGameReviewForThisGameException(String message) {
        super(message);
    }
}
