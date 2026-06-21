package com.food_delivery_app.user_service.Dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;

}
