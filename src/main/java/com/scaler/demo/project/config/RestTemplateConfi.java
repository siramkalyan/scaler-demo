package com.scaler.demo.project.config;

import com.scaler.demo.project.controller.EncryptedStringType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfi {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
