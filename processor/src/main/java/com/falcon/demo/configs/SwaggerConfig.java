package com.falcon.demo.configs;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return and(
                or(regex("/v1.*")));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("Microservice to collect info.")
                .title("Processor")
                .version("1.0")
                .contact(new Contact("juan", "https://github.com/jpganz", "jpganz18@gmail.com"))
                .build();
    }

    @SuppressWarnings("unused")
    @Controller
    public static class RedirectToSwagger {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String redirectToSwaggerUi() {
            return "redirect:/swagger-ui.html";
        }
    }
}
