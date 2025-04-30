package com.scaler.demo.project.order.mapper;

import com.scaler.demo.project.order.dtos.OrderDto;
import com.scaler.demo.project.order.dtos.OrderItemDto;
import com.scaler.demo.project.order.models.Order;
import com.scaler.demo.project.order.models.OrderItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderModelMapper {

    public OrderDto convertToDto(Order order){
        return  OrderDto.builder()
                .id(order.getOrderId())
                .items(order.getOrderItems().stream().map(this::convertToItemDto).toList())
                .build();
    }

    public OrderItemDto convertToItemDto(OrderItem item){
        return OrderItemDto.builder()
                .price(item.getPrice())
                .productBrand(item.getProduct().getBrand())
                .productId(item.getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .build();
    }
}
