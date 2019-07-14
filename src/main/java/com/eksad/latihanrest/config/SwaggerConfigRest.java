package com.eksad.latihanrest.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfigRest 
{
	@Bean
	public Docket eksadRestAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.latihanrest"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo())
				.tags(
					new Tag("Brand", "Brand Management API"),
					new Tag("Product", "Product Management API"),
					new Tag("Get Data API",""),
					new Tag("Data Manipulation API","")
					);
		}
	
	private ApiInfo metaInfo()
	{
		ApiInfo apiInfo = new ApiInfo(
				"Rest Data Management REST API",
				"Rest API Collection for Brand Data Management",
				"1.0.0",
				"http ://google.com",
				new Contact( "NH Anggraeni", "http://www.NhAnggraeni.com", "nhanggraeni@gmail.com"),
				"Anggraeni 2.0",
				"http://www.google.com/License",
				Collections.emptyList());
		
			return apiInfo;
	}
		
}
