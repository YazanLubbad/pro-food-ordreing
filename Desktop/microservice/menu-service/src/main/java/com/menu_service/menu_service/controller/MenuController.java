package com.menu_service.menu_service.controller;

import com.menu_service.menu_service.model.MenuItem;
import com.menu_service.menu_service.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Get all items
    @GetMapping("/")
    public ResponseEntity<List<MenuItem>> all() {
        List<MenuItem> menuItems = this.menuService.getAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // Get item by id
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getById(@PathVariable Long id) {
        MenuItem retrievedItem = this.menuService.getItemById(id);
        return new ResponseEntity<>(retrievedItem, HttpStatus.OK);
    }

    // Add new item
    @PostMapping("/add")
    public ResponseEntity<MenuItem> add(@RequestBody MenuItem menuItem) {
        MenuItem savedItem = this.menuService.addMenuItem(menuItem);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
}
