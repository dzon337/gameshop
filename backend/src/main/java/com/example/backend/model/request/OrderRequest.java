package com.example.backend.model.request;

import com.example.backend.model.order.EShippingMethod;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
    private String username;
    private String shippingMethod;
    private String quantity;
    private String gameId;
    private String orderDateTime;
}
