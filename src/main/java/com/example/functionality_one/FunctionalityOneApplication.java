package com.example.functionality_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.functionality_one.repositories")
@ComponentScan({"com.example.functionality_one.repositories","com.example.functionality_one.controllers",
		"com.example.functionality_one.DTOs","com.example.functionality_one.entities",
		"com.example.functionality_one.services"})
public class FunctionalityOneApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(FunctionalityOneApplication.class, args);
		System.out.println("running!");
	}

}
