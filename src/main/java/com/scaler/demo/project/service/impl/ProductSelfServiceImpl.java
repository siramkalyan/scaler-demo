package com.scaler.demo.project.service.impl;

import com.scaler.demo.project.model.Category;
import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.repository.ICategoryRepository;
import com.scaler.demo.project.repository.IProductRepository;
import com.scaler.demo.project.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSelfServiceImpl implements ProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Product findByProductId(Long id) {
        Optional<Product> productOptional =  repository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException();
        }
        return productOptional.isPresent() ? productOptional.get() : null;
    }

    @Override
    @Transactional
    public Product addNewproduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
       Category category = categoryOptional.get();
        List<Product> productList = category.getProducts();
        for (Product product1 : productList) {
            System.out.println(product1.getName());
        }
//        return null;

        if(categoryOptional.isEmpty()){
            product.setCategory(categoryRepository.save(product.getCategory()));
        }else{
            product.setCategory(categoryOptional.get());
        }
        return repository.save(product);
    }
}
