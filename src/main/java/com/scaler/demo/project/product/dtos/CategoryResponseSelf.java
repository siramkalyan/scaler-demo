package com.scaler.demo.project.product.dtos;

import com.scaler.demo.project.product.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponseSelf {
    private Category category;
    private String message;
}
