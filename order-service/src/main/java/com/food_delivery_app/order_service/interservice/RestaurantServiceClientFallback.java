package com.food_delivery_app.order_service.interservice;

import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RestaurantServiceClientFallback implements RestaurantClient{

    @Override
    public Object getRestaurantById(UUID id) {
        throw new RuntimeException("Restaurant service is unavailable. Please try later.");
    }
}
