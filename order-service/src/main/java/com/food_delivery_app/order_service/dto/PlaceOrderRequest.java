package com.food_delivery_app.order_service.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlaceOrderRequest {

    private UUID restaurantId;
    private List<OrderItemRequest> items;
}
