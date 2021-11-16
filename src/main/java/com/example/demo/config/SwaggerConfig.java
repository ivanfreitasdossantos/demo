package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig  {

    private static final String IO_PLATFORM_CLIENT_RESOURCE = "com.example.demo";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.demo" ))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("Client API")
            .description("testing description")
            .version("1.0")
            .contact(contact())
            .build();
    }

    private Contact contact(){
        return new Contact("Ivan freitas",
                "https://github.com/ivanfreitasdossantos",
                "ivan@tenil.com");
    }
    

    

}
