package com.example.backend.controller;

import com.example.backend.model.order.Order;
import com.example.backend.model.request.OrderRequest;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    // Additional endpoints for order management
}
