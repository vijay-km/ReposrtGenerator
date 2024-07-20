package com.example.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableScheduling
@OpenAPIDefinition(
		info = @Info(
				title = "Report microservice REST API Documentation",
				description = "Natwest Assignment Report microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Vijay Maurya",
						email = "vijaymaurya912082@gmail.com",
						url = "https://linkedin.com/in/vijay-maurya9120"
				)
//				,
//				license = @License(
//						name = "Apache 2.0",
//						url = "https:/"
//				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Natwest Assignment Report microservice REST API Documentation",
				url = "https://curaaid.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
