package com.food_delivery_app.order_service.repository;

import com.food_delivery_app.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUserEmail(String userEmail);
}
