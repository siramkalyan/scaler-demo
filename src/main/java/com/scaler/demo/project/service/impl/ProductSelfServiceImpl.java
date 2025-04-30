package com.scaler.demo.project.service.impl;

import com.scaler.demo.project.exceptions.ResourceNotFoundException;
import com.scaler.demo.project.model.Category;
import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.model.UserDto;
import com.scaler.demo.project.repository.ICategoryRepository;
import com.scaler.demo.project.repository.IProductRepository;
import com.scaler.demo.project.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductSelfServiceImpl implements ProductService {

    @Autowired
    private IProductRepository repository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public Product getProductDetails(Long uid, Long pid) {
        Product product =  repository.findProductById(pid);

//        UserDto userDto = restTemplate.getForEntity("http://userservice/users/{id}", UserDto.class, uid).getBody();
//        log.info(userDto.getEmailId());
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete,
                        () -> {throw new ResourceNotFoundException("Product not found!");});
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return  repository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String category, String name) {
        return List.of();
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0L;
    }
}
