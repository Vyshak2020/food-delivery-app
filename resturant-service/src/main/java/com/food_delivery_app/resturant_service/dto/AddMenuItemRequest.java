package com.food_delivery_app.resturant_service.dto;

import lombok.Data;

@Data
public class AddMenuItemRequest {

    private String name;
    private double price;
}
