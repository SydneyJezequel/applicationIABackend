package com.example.CrudTraining.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;






/**
 * Classe de configuration Cors.
 * Cette classe définit :
 * - Les requêtes acceptées (origines, type, etc.)
 * - Le template de requête pour chatGPT
 *
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {





    // *************************** Configuration Cors *************************** //

    /**
     * Méthode qui définit la configuration Cors
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // chemin de l'API
                .allowedOrigins("http://localhost:4200", "https://api.openai.com", "http://localhost:8008")// Origine autorisée (http://localhost:8081)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
                .allowedHeaders("Origin", "Content-Type", "Authorization", "Accept"); // En-têtes autorisés
    }





    // *************************** Attributs *************************** //

    // Url Api ChatGpt :
    @Value("${openai.api.key}")
    private String openApiKey;


    /**
     * Méthode qui définit le template utilisée par les requêtes pour chatGpt.
     * @return
     *
     */
    @Bean
    public RestTemplate template(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }





}

