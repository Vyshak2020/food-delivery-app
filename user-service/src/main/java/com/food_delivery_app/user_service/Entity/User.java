package com.food_delivery_app.user_service.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    private String password;

    private String role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
