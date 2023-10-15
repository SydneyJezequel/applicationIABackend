package com.example.CrudTraining.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Définissez ici le chemin de votre API
                .allowedOrigins("http://localhost:4200")// Origine autorisée (http://localhost:8081)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
                .allowedHeaders("Origin", "Content-Type", "Authorization", "Accept"); // En-têtes autorisés
    }


}
