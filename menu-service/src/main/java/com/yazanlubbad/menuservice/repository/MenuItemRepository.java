package com.yazanlubbad.menuservice.repository;

import com.yazanlubbad.menuservice.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
