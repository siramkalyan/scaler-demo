package com.scaler.demo.project.cartAndCheckout.dtos;


import com.scaler.demo.project.product.dtos.ProductDto;

import java.math.BigDecimal;

public class CartItemRequestDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;
}
