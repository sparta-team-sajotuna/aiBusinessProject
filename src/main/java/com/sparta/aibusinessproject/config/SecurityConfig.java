//package com.sparta.aibusinessproject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // CSRF 설정 및 시큐리티 기본 설정 비활성화
//        http.csrf((csrf) -> csrf.disable());
//        http.formLogin((formLogin) -> formLogin.disable());
//        http.httpBasic((httpBasic) -> httpBasic.disable());
//
//    }
//}
