package com.scaler.demo.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scaler.demo.project.controller.EncryptedStringType;
import com.scaler.demo.project.dto.TestConvertor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcType;
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

    @Type(EncryptedStringType.class)
    private String name;

    private String email;

    public void setName(String name){
        this.name = name;
    }



}
