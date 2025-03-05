package com.scaler.demo.project.repositories;

import com.scaler.demo.project.model.Category;
import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.repository.ICategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryRepoTest {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Test
    void testCategories(){
      Optional<Category> categoryOptional =  iCategoryRepository.findById(1L);
       Category category = categoryOptional.get();
        List<Product> productList = category.getProducts();


    }
}
