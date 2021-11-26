package com.zust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

  private CorsConfiguration buildCorsConfig() {
    CorsConfiguration ccfg = new CorsConfiguration();
    ccfg.addAllowedOrigin("http://localhost:8080");
    ccfg.addAllowedHeader("*");
    ccfg.addAllowedMethod("*");
    return ccfg;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", buildCorsConfig());
    return new CorsFilter(source);
  }
}
