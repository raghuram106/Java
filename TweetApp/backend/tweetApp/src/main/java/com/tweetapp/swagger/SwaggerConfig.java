package com.tweetapp.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
//@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
    
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.tweetapp"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(metaData());
        }


      private ApiInfo metaData() {
            return new ApiInfoBuilder()
                    .title("TweetApp")
                    .description("\"It was a tweet app we can post tweet, see the Tweets and we can replay to any person tweet it this application \"")
                    .version("1")
                    .license("Free one")
                    .licenseUrl("")
                    .contact(new Contact("Raghu", "", "TweetAppinfo@gmail.com"))
                    .build();
        }
        //for Swagger api doc generation
        
        //http://localhost:8080/swagger-ui.html#
        //http://localhost:8080/v2/api-docs
}