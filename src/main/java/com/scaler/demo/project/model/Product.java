package com.scaler.demo.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product {
    @Id
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Category category;

    private String imageUrl;
}
