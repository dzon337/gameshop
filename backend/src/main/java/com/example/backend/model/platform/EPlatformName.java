package com.example.backend.model.platform;

public enum EPlatformName {
    PLAYSTATION_5("Playstation 5"),
    PLAYSTATION_4("Playstation 4"),
    PLAYSTATION_2("Playstation 2"),
    XBOX360("Xbox 360"),
    XBOX_SERIES_X("Xbox series X"),
    NINTENDO_SWITCH("Nintendo switch"),
    Personal_Computer("Personal computer");

    private final String platformName;

    private EPlatformName(final String platformName) {
        this.platformName = platformName;
    }

    @Override
    public String toString() {
        return this.platformName;
    }
}