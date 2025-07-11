package com.example.project_spring_boot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringBootApplication.class, args);
	}

	@PostConstruct
	public void checkEnv(){
		System.out.println("DB_URL: " + System.getenv("DB_URL"));
	}

}
