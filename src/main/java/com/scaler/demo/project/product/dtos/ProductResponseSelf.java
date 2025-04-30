package com.scaler.demo.project.product.dtos;


import com.scaler.demo.project.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseSelf {
    private Product product;
    private String message;
}
