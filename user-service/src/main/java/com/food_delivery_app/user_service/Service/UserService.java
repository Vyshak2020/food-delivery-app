package com.food_delivery_app.user_service.Service;

import com.food_delivery_app.user_service.Dto.LoginRequest;
import com.food_delivery_app.user_service.Dto.RegisterRequest;
import com.food_delivery_app.user_service.Entity.User;
import com.food_delivery_app.user_service.Repo.UserRepository;
import com.food_delivery_app.user_service.exception.CustomException;
import com.food_delivery_app.user_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void registerUser(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new CustomException("Phone number already exists", HttpStatus.BAD_REQUEST);
        }

        // Check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("Phone number already exists");
        }

        // Create user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

}
