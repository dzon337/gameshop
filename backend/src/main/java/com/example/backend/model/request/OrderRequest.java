package com.example.backend.model.request;

import com.example.backend.model.order.EShippingMethod;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private String username;
    private String shippingMethod;
    private String orderDateTime;
    private List<Item> items; // List of items

    // Define the Item class inside OrderRequest
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Item {
        private Long gameId; // Use Long for gameId
        private Integer quantity; // Use Integer for quantity
    }

    // Getters, setters, and other methods as needed
}
