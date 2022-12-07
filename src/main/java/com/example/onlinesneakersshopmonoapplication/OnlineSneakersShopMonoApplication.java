package com.example.onlinesneakersshopmonoapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication
public class OnlineSneakersShopMonoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSneakersShopMonoApplication.class, args);
    }
}
