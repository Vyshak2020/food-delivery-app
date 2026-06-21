package com.food_delivery_app.order_service.controller;


import com.food_delivery_app.order_service.dto.PlaceOrderRequest;
import com.food_delivery_app.order_service.entity.Order;
import com.food_delivery_app.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestBody PlaceOrderRequest request) {
        orderService.placeOrder(request);
        return "Order placed successfully";
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrdersForUser();
    }
}
