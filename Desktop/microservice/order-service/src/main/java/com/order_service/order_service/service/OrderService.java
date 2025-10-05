
package com.order_service.order_service.service;

import com.order_service.order_service.Repositories.OrderRepository;
import com.order_service.order_service.model.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private  OrderRepository orderRepository; 
    
    public List<Order> getAllOrders() {
        return  this.orderRepository.findAll();
    }
     
    public Order getOrderById(Long id) {
        return  this.orderRepository.findById(id).get();
    }
    
    public Order addOrder (Order order ){
        return this.orderRepository.save(order);
    }
}
