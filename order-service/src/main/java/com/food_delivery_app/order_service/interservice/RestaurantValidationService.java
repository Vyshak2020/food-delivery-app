package com.food_delivery_app.order_service.interservice;


import com.food_delivery_app.order_service.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantValidationService {

    private final RestaurantClient restaurantClient;

    @Retry(name = "restaurantService" , fallbackMethod = "fallbackRestaurant")
    @CircuitBreaker(name = "restaurantService")
    public void validateRestaurant(UUID restaurantId) {
        System.out.println("Calling restaurant service...");
        restaurantClient.getRestaurantById(restaurantId);
    }

    public void fallbackRestaurant(UUID restaurantId, Exception ex) {
        throw new CustomException(
                "Restaurant service is down, please try later",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
