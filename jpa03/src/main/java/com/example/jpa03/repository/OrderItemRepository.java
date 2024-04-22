package com.example.jpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa03.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}