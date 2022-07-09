package com.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Student ID - Test API", version = "1.0", description = "ID TEST DATA "))
public class SpringBootSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerApplication.class, args);
	}
	
}
