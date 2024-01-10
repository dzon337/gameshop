package com.example.backend.repository;

import com.example.backend.model.order.Order;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
