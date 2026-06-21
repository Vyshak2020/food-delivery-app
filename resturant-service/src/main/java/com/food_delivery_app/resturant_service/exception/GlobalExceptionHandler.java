package com.food_delivery_app.resturant_service.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getStatus().toString());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", "400 BAD_REQUEST");

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        response.put("message", message);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", "500 INTERNAL_SERVER_ERROR");
        response.put("message", ex.getMessage());

        return ResponseEntity.internalServerError().body(response);
    }

}
