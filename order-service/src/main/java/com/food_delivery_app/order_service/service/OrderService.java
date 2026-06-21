package com.food_delivery_app.order_service.service;


import com.food_delivery_app.order_service.dto.PlaceOrderRequest;
import com.food_delivery_app.order_service.entity.Order;
import com.food_delivery_app.order_service.entity.OrderItem;
import com.food_delivery_app.order_service.interservice.RestaurantClient;
import com.food_delivery_app.order_service.interservice.RestaurantValidationService;
import com.food_delivery_app.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestaurantClient restaurantClient;

    private final RestaurantValidationService restaurantValidationService;

    public void placeOrder(PlaceOrderRequest request) {


        restaurantValidationService.validateRestaurant(request.getRestaurantId());

        String userEmail = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();


        Order order = Order.builder()
                .userEmail(userEmail)
                .restaurantId(request.getRestaurantId())
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();


        List<OrderItem> items = request.getItems().stream()
                .map(itemRequest -> {
                    OrderItem item = new OrderItem();
                    item.setItemName(itemRequest.getItemName());
                    item.setQuantity(itemRequest.getQuantity());
                    item.setOrder(order);
                    return item;
                })
                .toList();

        order.setItems(items);


        orderRepository.save(order);
    }

    public List<Order> getOrdersForUser() {


        String userEmail = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return orderRepository.findByUserEmail(userEmail);
    }
}
