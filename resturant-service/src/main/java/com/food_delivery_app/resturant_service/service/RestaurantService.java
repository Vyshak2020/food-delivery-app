package com.food_delivery_app.resturant_service.service;

import com.food_delivery_app.resturant_service.dto.AddMenuItemRequest;
import com.food_delivery_app.resturant_service.dto.CreateRestaurantRequest;
import com.food_delivery_app.resturant_service.entity.MenuItem;
import com.food_delivery_app.resturant_service.entity.Restaurant;
import com.food_delivery_app.resturant_service.exception.CustomException;
import com.food_delivery_app.resturant_service.repository.MenuItemRepository;
import com.food_delivery_app.resturant_service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final MenuItemRepository menuItemRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(UUID id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public Restaurant addRestaurant(CreateRestaurantRequest request) {

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();

        return restaurantRepository.save(restaurant);
    }

    public MenuItem addMenuItem(UUID restaurantId, AddMenuItemRequest request) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException("Restaurant not found", HttpStatus.NOT_FOUND));

        MenuItem item = MenuItem.builder()
                .name(request.getName())
                .price(request.getPrice())
                .restaurant(restaurant)
                .build();

        return menuItemRepository.save(item);
    }

    public List<MenuItem> getMenuItems(UUID restaurantId) {


        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException("Restaurant not found", HttpStatus.NOT_FOUND));

        return menuItemRepository.findByRestaurantId(restaurantId);
    }
}
