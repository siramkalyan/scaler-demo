
package com.scaler.demo.project.repositories;

import com.scaler.demo.project.dto.ProductMap;
import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.model.projections.ProductWithIdAndName;
import com.scaler.demo.project.repository.IProductRepository;
import com.scaler.demo.project.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest

public class ProductRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);
    @Autowired
    private IProductRepository repository;


    @Test
    void testQueries(){
        List<ProductWithIdAndName> productsWithname = repository.getAllProductsIdAndName();

        for(ProductWithIdAndName product : productsWithname){
            log.info("product name {}",product.getName());
        }


        List<Map<String,Object>> productMap = repository.getAllProducts();
        List<ProductMap> products = JsonUtil.convertListOfMapTOListDTO(productMap, ProductMap.class);
        System.out.println(products);



    }
}