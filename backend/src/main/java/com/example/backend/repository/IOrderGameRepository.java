package com.example.backend.repository;

import com.example.backend.model.order.OrderGame;
import com.example.backend.model.order.OrderGameKey;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IOrderGameRepository extends JpaRepository<OrderGame, OrderGameKey> {
}