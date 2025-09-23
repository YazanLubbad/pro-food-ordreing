package com.yazanlubbad.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class MenuClient {
    private final RestTemplate restTemplate;
    private final String menuServiceUrl;

    public MenuClient(RestTemplate restTemplate, @Value("${menu.service.url}") String menuServiceUrl) {
        this.restTemplate = restTemplate;
        this.menuServiceUrl = menuServiceUrl;
    }

    public boolean existsById(Long id) {
        try {
            String url = menuServiceUrl + "/api/menu/items/" + id;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
    private final MenuClient menuClient;

public OrderService( /* other deps */, MenuClient menuClient) {
    this.menuClient = menuClient;
for (OrderItemDto item : orderDto.getItems()) {
    Long menuItemId = item.getMenuItemId();
    boolean exists = menuClient.existsById(menuItemId);
    if (!exists) {
        throw new IllegalArgumentException("Menu item not found: " + menuItemId);
    }
}

}

}
