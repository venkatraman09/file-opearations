package com.example.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.models.Model;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Venkatraman Athmanathan
 *
 */
@SpringBootApplication
@EnableSwagger2
public class FileAccessApplication {
	/**
	 * main method is the starting execution for the program
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileAccessApplication.class, args);
	}
	
	/**
	 * getDocket method is used for generation of Swagger Document
	 * @return Docket object
	 */
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.paths(PathSelectors.ant("/api/*")).apis(RequestHandlerSelectors
						.basePackage("com.example.demo")).build().apiInfo(getApiInfo());
		
	}
	
	/**
	 * getApiInfo is method is used to get API Info for the Swagger
	 * @return
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfo("File Manipulation API", "API For Manipulating Files", "", "Venkatraman", new Contact("Venkatraman", "", "avraman09@gmail.com"), "", "",Collections.EMPTY_LIST);
	}

}
