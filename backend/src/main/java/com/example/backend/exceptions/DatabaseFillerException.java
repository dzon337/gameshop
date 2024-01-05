package com.example.backend.exceptions;

public class DatabaseFillerException extends RuntimeException {
    public DatabaseFillerException() {
    }

    public DatabaseFillerException(String message) {
        super(message);
    }
}
