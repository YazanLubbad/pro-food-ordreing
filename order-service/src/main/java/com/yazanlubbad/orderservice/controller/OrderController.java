package com.yazanlubbad.orderservice.controller;

import com.yazanlubbad.orderservice.model.OrderEntity;
import com.yazanlubbad.orderservice.service.OrderService;
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
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        String customerId = payload.getOrDefault("customerId", "unknown").toString();
        List<Integer> items = (List<Integer>) payload.get("items");
        if (items == null || items.isEmpty()) {
            return ResponseEntity.badRequest().body("items required");
        }
        List<Long> itemIds = items.stream().map(Integer::longValue).toList();
        try {
            OrderEntity saved = orderService.createOrder(customerId, itemIds);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
