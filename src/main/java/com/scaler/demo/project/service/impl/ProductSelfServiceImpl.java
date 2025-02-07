package com.scaler.demo.project.service.impl;

import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.repository.IProductRepository;
import com.scaler.demo.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductSelfServiceImpl implements ProductService {

    @Autowired
    private IProductRepository repository;
    @Override
    public Product findByProductId(Long id) {
        Optional<Product> productOptional =  repository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException();
        }
        return productOptional.isPresent() ? productOptional.get() : null;
    }
}
