package com.scaler.demo.project.order.services;



import com.scaler.demo.project.order.dtos.OrderDto;
import com.scaler.demo.project.order.models.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getOrdersByUserId(Long userId);

    OrderDto convertToDto(Order order);
}
