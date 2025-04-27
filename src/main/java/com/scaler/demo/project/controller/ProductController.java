package com.scaler.demo.project.controller;

import com.scaler.demo.project.dto.ProductDTO;
import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private RestTemplate restTemplate;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product)
    {

        return productService.addNewproduct(product);
    }

    @GetMapping("/user/{uid}/product/{pid}")
    public Product getProductDetails(@PathVariable Long uid, @PathVariable Long pid){
        Product product =  productService.getProductDetails(uid, pid);

        return product;
    }

}
