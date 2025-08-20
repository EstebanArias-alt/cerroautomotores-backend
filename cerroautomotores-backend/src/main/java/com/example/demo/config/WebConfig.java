package com.example.demo.config; // O tu paquete de configuración

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // <-- MUY IMPORTANTE: Le dice a Spring que esta es una clase de configuración
public class WebConfig {

	@Bean
	 WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Aplica esta configuración a todas las rutas que empiecen con /api
                registry.addMapping("/api/**")
                        // Permite peticiones desde tu frontend Angular
                        .allowedOrigins("http://localhost:4200")
                        // Permite estos métodos HTTP
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Permite todas las cabeceras
                        .allowedHeaders("*")
                        // Permite el envío de credenciales (como cookies) si fuera necesario
                        .allowCredentials(true);
            }
        };
    }
}