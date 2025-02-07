package com.scaler.demo.project.dto;

import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class TestConvertor implements AttributeConverter<String,String> {
    @Override
    public String convertToDatabaseColumn(String s) {
        return "v1" + s + "v1";
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return s.replace("v1","");
    }
}
