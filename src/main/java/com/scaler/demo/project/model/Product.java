package com.scaler.demo.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{


    @Getter
    private String name;

    private String description;

    @Getter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private Category category;

    private String imageUrl;

}
