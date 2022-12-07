package com.example.onlinesneakersshopmonoapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main/index");
        registry.addViewController("/products").setViewName("product/index");
        registry.addViewController("/login").setViewName("auth/login");
    }
}
