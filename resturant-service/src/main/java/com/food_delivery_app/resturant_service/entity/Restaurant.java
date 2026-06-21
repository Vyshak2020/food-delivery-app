package com.food_delivery_app.resturant_service.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;
}