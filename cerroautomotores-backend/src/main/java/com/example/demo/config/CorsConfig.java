package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

@Configuration
public class CorsConfig {
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration cors = new CorsConfiguration();
    cors.setAllowedOrigins(List.of(
      "https://68a5f5ccb60c3b90480fa9c4--incandescent-selkie-9245c2.netlify.app/"   // ← poné tu URL real
      // "https://TU-SITIO.vercel.app" // si usás Vercel
    ));
    cors.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
    cors.setAllowedHeaders(List.of("*"));
    cors.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", cors);
    return new CorsFilter(source);
  }
}
