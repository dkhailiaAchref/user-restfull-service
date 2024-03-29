package com.achrefdkhailia.code.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;

/**
 * open  documentation api inerface on this url :
 * http://localhost:8080/swagger-ui.html#/
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.achrefdkhailia.code.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo())
                .tags(new Tag("users", "API permettant la manipulation des users.")
                );
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                " USERS CRUD API",
                "API project to expose CRUD on users to all apps"
                ,
                "1.0",
                "contact",
                null,
                null,
                null);
        return apiInfo;
    }
}