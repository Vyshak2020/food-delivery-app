# Food Delivery Application



## Overview



A microservices-based Food Delivery Platform built using Spring Boot.



The application is split into independent services responsible for user management, restaurant management, and order processing. The services communicate using OpenFeign and are designed with fault tolerance using Resilience4j.



## Services



### User Service



* User Registration

* User Login

* JWT Token Generation

* Role-Based Authorization



### Order Service



* Place Orders

* Validate Restaurants before ordering

* Communicate with Restaurant Service using OpenFeign

* Resilience4j Circuit Breaker and Retry



### Restaurant Service



* Restaurant Management

* Menu Management



### API Gateway



* Single Entry Point for all services



## Technologies Used



* Java 17

* Spring Boot

* Spring Security

* JWT Authentication

* Spring Data JPA

* PostgreSQL

* OpenFeign

* Resilience4j

* Docker

* Docker Compose

* Gradle



## Security



Implemented JWT-based authentication and authorization using Spring Security.



### Features



* Stateless Authentication

* JWT Token Validation

* BCrypt Password Encryption

* Role-Based Access Control



## Fault Tolerance



Implemented Resilience4j in Order Service.



### Features



* Retry Mechanism

* Circuit Breaker Pattern

* Fallback Handling



### Configuration



* Retry Attempts: 3

* Sliding Window Size: 5

* Failure Threshold: 50%

* Open State Duration: 10 Seconds



## Dockerized Services



Currently Dockerized:



* User Service

* Order Service

* PostgreSQL



Managed using Docker Compose.



### Run



bash

docker compose up --build





## Future Enhancements



* Dockerize Restaurant Service

* Dockerize API Gateway

* Add Service Discovery

* Add Monitoring and Logging

* Deploy to Cloud



## Learning Outcomes



This project helped me gain hands-on experience with:



* Microservices Architecture

* Spring Security

* JWT Authentication

* OpenFeign

* Resilience4j

* Docker

* Docker Compose

* PostgreSQL Integration

* Inter-Service Communication



