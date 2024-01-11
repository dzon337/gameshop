package com.example.backend.model.request;

import java.util.List;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String username;
    private String shippingMethod;
    private String orderDateTime;
    private List<Item> items;

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long gameId;
        private Integer quantity;
    }

}