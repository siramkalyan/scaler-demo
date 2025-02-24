package com.scaler.demo.project.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

@Slf4j
public class JsonUtil {
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setUp(){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }
    public static <T> List<T> convertListOfMapTOListDTO(Collection<?> collection, Class<T> boClass) {
        try {
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            return objectMapper.convertValue(collection, objectMapper.getTypeFactory().constructCollectionType(List.class, boClass));
        }
        catch (Exception ex){
            log.error("Convert of list of map to list Dto {} failed {}", boClass, ex.getMessage());
            throw new RuntimeException("Conversion failed" + ex.getMessage());
        }
    }
}
