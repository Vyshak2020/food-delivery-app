package com.food_delivery_app.order_service.interservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "restaurant-service",
        url = "http://localhost:8083"
)
public interface RestaurantClient {

    @GetMapping("/restaurants/{id}")
    Object getRestaurantById(@PathVariable UUID id);
}
