package com.sparta.aibusinessproject.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {

        Info info = new Info()
                .title("AiBusinessProject")
                .description("✨ AI를 활용한 배달 주문 시스템 ✨");


        return new OpenAPI()
                .info(info);
    }
}