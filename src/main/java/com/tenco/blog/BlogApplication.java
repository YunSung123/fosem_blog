package com.tenco.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		// http://localhost:8080/h2-console
		SpringApplication.run(BlogApplication.class, args);
	}

}
