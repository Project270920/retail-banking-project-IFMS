package com.project.ifms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class RetailBankingProjectIfmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingProjectIfmsApplication.class, args);
	}
}
