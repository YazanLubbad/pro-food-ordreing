package com.yazanlubbad.menuservice.service;

import com.yazanlubbad.menuservice.model.MenuItem;
import com.yazanlubbad.menuservice.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuItemRepository repo;

    public MenuService(MenuItemRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initData() {
        repo.save(new MenuItem("Cheese Pizza", 7.99, 10));
        repo.save(new MenuItem("Veg Burger", 5.49, 20));
        repo.save(new MenuItem("Coke", 1.99, 50));
    }

    public List<MenuItem> getAll() {
        return repo.findAll();
    }

    public Optional<MenuItem> getById(Long id) {
        return repo.findById(id);
    }
}
