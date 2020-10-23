package com.david.b2wstarwars.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final ResponseMessage m200 = simpleMessage(200, "Ok");
	private final ResponseMessage m201 = simpleMessage(201, "Created");
	private final ResponseMessage m204del = simpleMessage(204, "Deleted");
	private final ResponseMessage m403 = simpleMessage(403, "Not Authorized");
	private final ResponseMessage m404 = simpleMessage(404, "Not Found");
	private final ResponseMessage m422 = simpleMessage(422, "Validation Error");
	private final ResponseMessage m500 = simpleMessage(500, "Server error");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m403, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m403, m404, m422, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.david.b2wstarwars.resources"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API de cadastro de planetas de Star Wars", "Esta API foi criada devido a desafio da B2W",
				"Versão 1.0.0", "David Terms", new Contact("David Luna", "com/david", "david.luna.ads@gmail.com"),
				"Desafio da B2W", "David Terms", Collections.emptyList() // Vendor
																			// Extensions
		);
	}

	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}
}