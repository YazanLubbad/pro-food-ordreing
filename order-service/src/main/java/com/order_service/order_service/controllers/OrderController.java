
package com.order_service.order_service.controllers;

import com.order_service.order_service.VO.ResponseTemplateVO;
import com.order_service.order_service.model.Order;
import com.order_service.order_service.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = this.orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(this.orderService.addOrder(order), HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> getOrderWhititemByOrderId(@PathVariable("id") Long orderId){
       ResponseTemplateVO vo = this.
               orderService.getOrderWithItemByOrderId(orderId);
       return new ResponseEntity<>(vo,HttpStatus.OK);
   }

    
}
