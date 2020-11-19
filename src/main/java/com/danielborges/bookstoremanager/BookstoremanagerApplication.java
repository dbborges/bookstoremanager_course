package com.danielborges.bookstoremanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaAuditing
public class BookstoremanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoremanagerApplication.class, args);
	}

}
