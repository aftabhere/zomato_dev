package com.zomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.zomato.repository")
public class ZomatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZomatoApplication.class, args);
	}

}
