package com.food_delivery_app.resturant_service.dto;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
    private String name;
    private String location;
}
