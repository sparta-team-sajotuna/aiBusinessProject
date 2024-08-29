package com.sparta.aibusinessproject.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class EnvLoader {

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("JWT_SECRET_KEY", dotenv.get("JWT_SECRET_KEY"));
        System.setProperty("OWNER_TOKEN", dotenv.get("OWNER_TOKEN"));
//        System.setProperty("MANAGER_TOKEN", dotenv.get("MANAGER_TOKEN"));
//        System.setProperty("MASTER_TOKEN", dotenv.get("MASTER_TOKEN"));
    }
}

