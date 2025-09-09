package com.yazanlubbad.orderservice.repository;

import com.yazanlubbad.orderservice.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
