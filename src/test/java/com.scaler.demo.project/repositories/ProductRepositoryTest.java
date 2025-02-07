
package com.scaler.demo.project.repositories;

import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.model.projections.ProductWithIdAndName;
import com.scaler.demo.project.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class ProductRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);
    @Autowired
    private IProductRepository repository;

    @Test
    void testQueries(){
        List<ProductWithIdAndName> productsWithname = repository.getAllProducts();
        List<Product> products = repository.findAll();
        for(Product product : products){
            log.info("product name {}",product.getName());
        }

    }
}