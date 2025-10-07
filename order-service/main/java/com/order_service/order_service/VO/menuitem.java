package com.order_service.order_service.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class menuitem {
    private Long id;
    private String name;
    private Integer quantity;
    private String provider;
}
