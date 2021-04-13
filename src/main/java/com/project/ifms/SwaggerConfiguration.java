package com.project.ifms;

import java.util.Collections;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfiguration
{	
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.project.ifms"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"Transaction API",
				"Sample API for Spring Boot Swagger Tutorial",
				"1.0",
				"Free to user",
				new springfox.documentation.service.Contact("Aman Pradhan", "http://www.xyz.com", "aman.ipec2020@gmail.com"),
				"API License",
				"http://www.xyz.com",
				Collections.emptyList());
	}
}
