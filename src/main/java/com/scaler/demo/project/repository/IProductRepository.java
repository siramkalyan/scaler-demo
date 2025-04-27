package com.scaler.demo.project.repository;

import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.model.projections.ProductWithIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();


    @Query(value = "select id, name, description  from products", nativeQuery = true)
    List<ProductWithIdAndName> getAllProductsIdAndName();

    @Query(value = "select id,name from products", nativeQuery = true)
    List<Map<String,Object>> getAllProducts();

    @Query("SELECT * from products where id = ?1")
    Product findProductById(Long productId);

 //   Optional<Product> queryDistinctByIdId();
}
