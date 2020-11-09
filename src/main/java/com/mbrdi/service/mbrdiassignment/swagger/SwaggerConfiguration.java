package com.mbrdi.service.mbrdiassignment.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/search-service/.*")).build().apiInfo(apiInfo())
                .tags( new Tag( "Search Swagger", "Swagger Endpoints" ) ).tags( new Tag( "Search", "Endpoints" ) );
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Search API's").description("MBRDI Search API’s to find Nearest Location")
                .version("v1").contact(new Contact("Dhananjay Kumar", null, "dhananjay4058@gmail.com")).build();
    }

}