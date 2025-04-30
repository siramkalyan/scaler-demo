package com.scaler.demo.project.product.mapper;

import com.scaler.demo.project.product.dtos.ProductDto;
import com.scaler.demo.project.product.models.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public ProductDto convertToDto(Product product){
            return ProductDto.builder()
                    .id(product.getId())
                    .price(BigDecimal.TEN)
                    .name(product.getName())
                    .brand(product.getDescription())
                    .description(product.getDescription())
                    .build();
    }
}
