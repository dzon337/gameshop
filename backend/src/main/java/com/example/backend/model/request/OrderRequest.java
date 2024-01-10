package com.example.backend.model.request;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String username;
    private String shippingMethod;
    private String orderDateTime;
    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Item {
        private Long gameId;
        private Integer quantity;
    }

}