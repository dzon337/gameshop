package com.example.backend.model.order;

public enum EShippingMethod {
    EXPRESS ("Express Delivery"),
    STANDARD ("Standard delivery"),
    INTERNATIONAL ("International delivery");

    private final String method;

    private EShippingMethod(final String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return this.method;
    }
}