package com.food_delivery_app.resturant_service.controller;

import com.food_delivery_app.resturant_service.dto.AddMenuItemRequest;
import com.food_delivery_app.resturant_service.dto.CreateRestaurantRequest;
import com.food_delivery_app.resturant_service.entity.MenuItem;
import com.food_delivery_app.resturant_service.entity.Restaurant;
import com.food_delivery_app.resturant_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;


    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }


    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody CreateRestaurantRequest request) {
        return restaurantService.addRestaurant(request);
    }

    @PostMapping("/{restaurantId}/menu")
    public MenuItem addMenuItem(@PathVariable UUID restaurantId,
                                @RequestBody AddMenuItemRequest request) {
        return restaurantService.addMenuItem(restaurantId, request);
    }

    @GetMapping("/{restaurantId}/menu")
    public List<MenuItem> getMenuItems(@PathVariable UUID restaurantId) {
        return restaurantService.getMenuItems(restaurantId);
    }
}