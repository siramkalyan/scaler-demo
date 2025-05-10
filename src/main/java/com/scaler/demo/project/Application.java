package com.scaler.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =  SpringApplication.run(Application.class, args);

		List<String> beans = List.of(context.getBeanDefinitionNames());

		for(String bean : beans){
			System.out.println("Bean: " + bean);
		}
	}

}
