package br.com.ufg.listaplic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    private static final String API_NAME = "List Aplic API";
    private static final String API_DESCRIPTION = "This is the List Aplic API";
    private static final String API_VERSION = "v1";
    private static final String API_CONTACT = "isaias_neto@discente.ufg.br";
    private static final String API_LICENSE = "API License";
    private static final String API_LICENSE_URL = "http://www.google.com";
    private static final String API_CONTROLLER_PACKAGE = "br.com.ufg.listaplic.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(API_NAME)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION)
                        .license(API_LICENSE)
                        .licenseUrl(API_LICENSE_URL)
                        .contact(new Contact("", "", API_CONTACT))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(API_CONTROLLER_PACKAGE))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false);
    }
}
