package com.example.backend.service;

import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDateTime;

import java.util.Set;
import java.util.HashSet;

import com.example.backend.model.user.User;
import com.example.backend.model.order.Order;
import com.example.backend.model.order.OrderGame;
import com.example.backend.model.order.OrderGameKey;
import com.example.backend.model.request.OrderRequest;
import com.example.backend.repository.IGameRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.repository.IOrderRepository;
import com.example.backend.model.order.EShippingMethod;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.exceptions.GameDoesNotExistException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Transactional
    public Order createOrder(final OrderRequest orderRequest) {
        final User user = userRepository
                .findUserByUsername(orderRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User: " + orderRequest.getUsername() + " not found!"));

        final LocalDateTime orderDate = LocalDateTime.ofInstant(
                Instant.parse(orderRequest.getOrderDateTime()),
                ZoneId.systemDefault()
        );

        final Long orderId = Order.getID();
        final Set<OrderGame> orderGames = new HashSet<>();
        for (OrderRequest.Item item : orderRequest.getItems()) {
            var possibleGame = gameRepository
                    .findById(item.getGameId())
                    .orElseThrow(() -> new GameDoesNotExistException("Game not found for ID: " + item.getGameId()));

            final OrderGameKey orderGameKey = new OrderGameKey(orderId, possibleGame.getGameId());
            final OrderGame orderGame = OrderGame.builder()
                                                    .orderGameId(orderGameKey)
                                                    .game(possibleGame)
                                                    .quantity(item.getQuantity())
                                                    .build();
            orderGames.add(orderGame);
        }

        final Order order = Order.builder()
                                    .orderId(orderId)
                                    .shippingMethod(EShippingMethod.valueOf(orderRequest.getShippingMethod()))
                                    .orderDate(orderDate)
                                    .user(user)
                                    .orderGames(orderGames)
                                    .build();

        orderGames.forEach(orderGame -> orderGame.setOrder(order));

        orderRepository.save(order);
        return order;
    }

}