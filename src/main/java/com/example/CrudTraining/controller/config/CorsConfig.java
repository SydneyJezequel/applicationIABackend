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
 * - Les requêtes acceptées par le Backend (origines, type, etc.)
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
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200", "https://api.openai.com", "http://localhost:8008")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Content-Type", "Authorization", "Accept");
    }





    // *************************** Attributs *************************** //

    // Url et Key de l'Api ChatGpt :
    @Value("${openai.api.key}")
    private String openApiKey;





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui définit le template utilisé par les requêtes pour chatGpt.
     * @return Template Rest des requêtes HTTP.
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

