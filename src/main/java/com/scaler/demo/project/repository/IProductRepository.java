package com.scaler.demo.project.repository;

import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.model.projections.ProductWithIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    @Query("select p.id as id, p.name as name from Product p")
    List<ProductWithIdAndName> getAllProducts();

 //   Optional<Product> queryDistinctByIdId();
}
