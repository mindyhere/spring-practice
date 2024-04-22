package com.example.jpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa03.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
}
