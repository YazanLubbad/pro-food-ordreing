package com.yazanlubbad.orderservice.service;

import com.yazanlubbad.orderservice.model.OrderEntity;
import com.yazanlubbad.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final WebClient webClient;

    public OrderService(OrderRepository repo, WebClient.Builder webClientBuilder) {
        this.repo = repo;
        this.webClient = webClientBuilder.build();
    }

    public OrderEntity createOrder(String orderId, List<Long> itemIds) {
        // validate each item via MenuService
        double total = 0.0;
        for (Long id : itemIds) {
            try {
                MenuItemDto dto = webClient.get()
                        .uri("http://localhost:8081/api/menu/{id}", id)
                        .retrieve()
                        .bodyToMono(MenuItemDto.class)
                        .block();

                if (dto == null) {
                    throw new RuntimeException("Menu item not found: " + id);
                }
                total += dto.getPrice();
            } catch (Exception e) {
                throw new RuntimeException("Validation failed for item: " + id + " -> " + e.getMessage());
            }
        }
        OrderEntity order = new OrderEntity();
        order.setOrderId(orderId);
        order.setTotalPrice(total);
        order.setItemIds(itemIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        return repo.save(order);
    }

    // DTO for calling MenuService
    public static class MenuItemDto {
        private Long id;
        private String name;
        private double price;
        private int quantity;

        public MenuItemDto() {}

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
