package com.example.functionality_two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "com.example.functionality_two.repositories")
@ComponentScan({"com.example.functionality_two.repositories","com.example.functionality_two.controllers",
		"com.example.functionality_two.DTOs", "com.example.functionality_two.entities",
		"com.example.functionality_two.services"})
public class FunctionalityOneApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(FunctionalityOneApplication.class, args);
		System.out.println("running!");
	}

}
