package com.example.demo.Repository;

import com.example.demo.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // No unnecessary methods added; clean and follows standard JpaRepository usage
}