package com.example.backend.service;

import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDateTime;

import java.util.Set;
import java.util.List;
import java.util.HashSet;

import com.example.backend.model.order.Order;
import com.example.backend.model.order.OrderGame;
import com.example.backend.model.order.OrderGameKey;
import com.example.backend.model.request.OrderRequest;
import com.example.backend.repository.IGameRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.repository.IOrderRepository;
import com.example.backend.model.order.EShippingMethod;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IGameRepository gameRepository;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository, IGameRepository gameRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        var user = userRepository.findUserByUsername(orderRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDateTime orderDate = LocalDateTime.ofInstant(
                Instant.parse(orderRequest.getOrderDateTime()),
                ZoneId.systemDefault()
        );

        Set<OrderGame> orderGames = new HashSet<>();

        for (OrderRequest.Item item : orderRequest.getItems()) {
            var game = gameRepository.findById(item.getGameId())
                    .orElseThrow(() -> new RuntimeException("Game not found for ID: " + item.getGameId()));

            var orderGameKey = new OrderGameKey(null, game.getGameId());
            var orderGame = OrderGame.builder()
                    .orderGameId(orderGameKey)
                    .game(game)
                    .quantity(item.getQuantity())
                    .build();

            orderGames.add(orderGame);
        }

        var order = Order.builder()
                .user(user)
                .shippingMethod(EShippingMethod.valueOf(orderRequest.getShippingMethod()))
                .order_date(orderDate)
                .orderGames(orderGames)
                .build();

        orderGames.forEach(orderGame -> orderGame.setOrder(order));

        return orderRepository.save(order);
    }

}