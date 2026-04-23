package com.capstone.warehouse.dto;

import com.capstone.warehouse.entity.Order;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Integer id;
    private LocalDateTime orderDate;
    private Order.Status status;
    private String userName;

    public static OrderDTO from(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setUserName(order.getUser() != null ? order.getUser().getName() : null);
        return dto;
    }
}