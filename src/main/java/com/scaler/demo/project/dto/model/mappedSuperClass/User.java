package com.scaler.demo.project.dto.model.mappedSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user")
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
}
