
package com.order_service.order_service.service;

import com.order_service.order_service.Repositories.OrderRepository;
import com.order_service.order_service.VO.ResponseTemplateVO;
import com.order_service.order_service.VO.menuitem;
import com.order_service.order_service.model.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private  OrderRepository orderRepository; 
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Order> getAllOrders() {
        return  this.orderRepository.findAll();
    }
     
    public Order getOrderById(Long id) {
        return  this.orderRepository.findById(id).get();
    }
    
    public Order addOrder (Order order ){
        return this.orderRepository.save(order);
    }

   public ResponseTemplateVO getOrderWithItemByOrderId(Long orderId) {
        Order order = this.getOrderById(orderId);
        menuitem item = this.restTemplate
                .getForObject("http://ITEM-SERVICE:9001/menu/" + 
                        order.getItem_id(), menuitem.class);

        ResponseTemplateVO vo = new ResponseTemplateVO();

        vo.setOrder(order);
        vo.setMenuitem(item);

        return vo;

    }


}
