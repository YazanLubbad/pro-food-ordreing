package com.menu_service.menu_service.service;

import com.menu_service.menu_service.model.MenuItem;
import com.menu_service.menu_service.repository.MenuItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAll() {
        return menuItemRepository.findAll();
    }

    public MenuItem getItemById(long id) {
        return this.menuItemRepository.findById(id).orElse(null);
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
        return this.menuItemRepository.save(menuItem);
    }
}
