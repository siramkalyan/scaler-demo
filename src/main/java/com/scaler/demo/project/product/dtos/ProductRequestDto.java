package com.scaler.demo.project.product.dtos;

import com.scaler.demo.project.product.models.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequestDto {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDto> images;
}

