package com.dispatch.dispatchloadbalancer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to enable Cross-Origin Resource Sharing (CORS) globally.
 * This allows the backend to handle requests from different domains,
 * ensuring seamless communication with frontend applications or external clients.
 */
@Configuration
public class CorsConfig {

    /**
     * Defines a CORS configuration bean that customizes allowed origins,
     * HTTP methods, and headers for all API endpoints.
     *
     * @return a WebMvcConfigurer instance with CORS settings applied.
     */

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Configures global CORS settings to allow cross-origin requests from any domain.
             *
             * @param registry the CORS registry to configure mappings.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Applies CORS to all endpoints.
                        .allowedOrigins("*")  // Allows requests from any origin.
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permits common HTTP methods.
                        .allowedHeaders("*");  // Accepts all headers in requests.
            }
        };
    }
}
