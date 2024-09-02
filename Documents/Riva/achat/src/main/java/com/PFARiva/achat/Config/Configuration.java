package com.PFARiva.achat.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@org.springframework.context.annotation.Configuration


public class Configuration implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer CorsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Applique la configuration à tous les chemins
                        .allowedOrigins("http://localhost:3000,http://192.168.56.1:3000") // Liste des origines autorisées (frontend)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes HTTP autorisées
                        .allowedHeaders("*") // En-têtes autorisés
                        .allowCredentials(true); // Autorise les cookies et les informations d'identification
            }
        };
    }
}