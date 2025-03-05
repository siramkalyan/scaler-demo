package com.scaler.demo.project.service;

import com.scaler.demo.project.model.Product;

public interface ProductService {
    Product findByProductId(Long id);
    Product addNewproduct(Product product);
}
