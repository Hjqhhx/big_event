package com.itheima.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("big-event")
                        .version("1.0")
                        .description("项目学习")
                        .termsOfService("https://test.com")
                        .contact(new Contact().name("hjq").url("https://test.com").email("test@gamil.com")));

    }
}
