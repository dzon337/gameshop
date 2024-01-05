package com.example.backend.service;

import com.example.backend.model.order.EShippingMethod;
import com.example.backend.model.order.Order;
import com.example.backend.model.order.OrderGame;
import com.example.backend.model.order.OrderGameKey;
import com.example.backend.model.request.OrderRequest;
import com.example.backend.repository.IOrderRepository;
import com.example.backend.repository.IGameRepository;
import com.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    private final static List<EShippingMethod> SHIPPING_METHODS = List.of(EShippingMethod.values());

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IGameRepository gameRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository, IGameRepository gameRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        var user = userRepository.findUserByUsername(orderRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var game = gameRepository.findById(Long.parseLong(orderRequest.getGameId()))
                .orElseThrow(() -> new RuntimeException("Game not found"));

        var orderGameKey = new OrderGameKey(null, game.getGameId());
        var orderGame = OrderGame.builder()
                .orderGameId(orderGameKey)
                .game(game)
                .quantity(Integer.parseInt(orderRequest.getQuantity()))
                .build();
        LocalDateTime orderDate = LocalDateTime.ofInstant(
                Instant.parse(orderRequest.getOrderDateTime()),
                ZoneId.systemDefault()
        );
        var orderGames = new HashSet<OrderGame>();
        orderGames.add(orderGame);

        System.out.println(orderRequest.getShippingMethod());
        var order = Order.builder()
                .user(user)
                .shippingMethod(EShippingMethod.valueOf(orderRequest.getShippingMethod())) // Convert or map to EShippingMethod enum
                .order_date(orderDate) // Ensure the format matches
                .orderGames(orderGames)
                .build();

        orderGame.setOrder(order); // Set the order in OrderGame

        return orderRepository.save(order);
    }

    // Additional methods for order management
}
