package com.yazanlubbad.orderservice.service;

import com.yazanlubbad.orderservice.model.OrderEntity;
import com.yazanlubbad.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final WebClient webClient;

    @Value("${menu.service.url}")
    private String menuServiceUrl;

    public OrderService(OrderRepository repo, WebClient.Builder webClientBuilder) {
        this.repo = repo;
        this.webClient = webClientBuilder.build();
    }

    public OrderEntity createOrder(String customerId, List<Long> itemIds) {
        // validate each item via MenuService
        List<MenuItemDto> items = itemIds.stream()
                .map(this::fetchMenuItem)
                .collect(Collectors.toList());

        // build order entity
        OrderEntity order = new OrderEntity();
        order.setCustomerId(customerId);
        order.setCreatedAt(Instant.now().toString());

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        order.setTotal(total);

        order.setItemIds(itemIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));

        return repo.save(order);
    }

    private MenuItemDto fetchMenuItem(Long id) {
        try {
            return webClient.get()
                    .uri(menuServiceUrl + "/api/menu/items/{id}", id)
                    .retrieve()
                    .bodyToMono(MenuItemDto.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            throw new RuntimeException("Menu item not found: " + id);
        } catch (Exception ex) {
            throw new RuntimeException("Error calling MenuService: " + ex.getMessage(), ex);
        }
    }

    // DTO for MenuService response
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
