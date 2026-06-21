package com.food_delivery_app.user_service.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
