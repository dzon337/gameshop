package com.example.backend.model.genre;

public enum EGenreName {
    SHOOTER ("Shooter"),
    SPORTS ("Sports"),
    ADVENTURE ("Adventure"),
    OPEN_WORLD ("Open world"),
    ACTION ("Action"),
    ROLE_PLAY ("Role play"),
    RACING("Racing"),
    FIGHTING ("Fighting"),
    HORROR ("Horror"),
    MULTIPLAYER ("Multiplayer");

    private final String genreName;

    private EGenreName(final String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return this.genreName;
    }

}