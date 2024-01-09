package com.example.backend.exceptions;

public class GameDoesNotExistException extends RuntimeException {

    public GameDoesNotExistException() {
    }

    public GameDoesNotExistException(String message) {
        super(message);
    }

}
