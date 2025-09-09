package com.yazanlubbad.orderservice.controller;

import com.yazanlubbad.orderservice.service.OrderService;
import com.yazanlubbad.orderservice.model.OrderEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        String orderId = (String) body.getOrDefault("orderId", "o-" + System.currentTimeMillis());
        List<Integer> items = (List<Integer>) body.get("items");

        if (items == null || items.isEmpty()) {
            return ResponseEntity.badRequest().body("items required");
        }

        List<Long> itemIds = items.stream().map(Integer::longValue).toList();

        try {
            OrderEntity saved = orderService.createOrder(orderId, itemIds);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
