package com.example.backend.model.platform;

public enum EManufacturer {
    SONY ("Sony"),
    MICROSOFT ("Microsoft"),
    PC ("PC"),
    NINTENDO ("Nintendo");

    private final String manufacturer;

    private EManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return this.manufacturer;
    }
}