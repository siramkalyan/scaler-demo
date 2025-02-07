package com.scaler.demo.project.model;

import com.scaler.demo.project.dto.TestConvertor;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.stereotype.Component;

public class TestEntity {

    @Convert(converter = TestConvertor.class)
    private String time1;
    private String test1;

}
