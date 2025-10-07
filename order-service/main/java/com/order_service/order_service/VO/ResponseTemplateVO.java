package com.order_service.order_service.VO;

import com.order_service.order_service.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Order order;
    private  menuitem menuitem;
}
