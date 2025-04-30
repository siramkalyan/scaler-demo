package com.scaler.demo.project.service;

import com.scaler.demo.project.model.Product;

import java.util.List;

public interface ProductService {
    Product findByProductId(Long id);
    Product addNewproduct(Product product);
    Product getProductDetails(Long uid, Long pid);
    void deleteProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);

}
