package com.example.jpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa03.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}