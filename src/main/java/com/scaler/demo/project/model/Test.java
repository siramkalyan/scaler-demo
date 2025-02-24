package com.scaler.demo.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity(name = "ms_test")
@Getter
@Setter
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Long id;

    @Setter
    @JsonProperty("name")
    private String name;

    private String email;

}
