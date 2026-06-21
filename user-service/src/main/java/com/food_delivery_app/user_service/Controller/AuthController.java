package com.food_delivery_app.user_service.Controller;


import com.food_delivery_app.user_service.Dto.LoginRequest;
import com.food_delivery_app.user_service.Dto.RegisterRequest;
import com.food_delivery_app.user_service.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        String token = userService.login(request);

        log.info("This is the token "+token);

        return Map.of("token", token);
    }
}
