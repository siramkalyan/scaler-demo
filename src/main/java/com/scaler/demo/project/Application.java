package com.scaler.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =  SpringApplication.run(Application.class, args);

		List<String> beans = List.of(context.getBeanDefinitionNames());

		for(String bean : beans){
			System.out.println("Bean: " + bean);
		}
	}

}
