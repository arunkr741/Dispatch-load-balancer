package com.dispatch.dispatchloadbalancer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger/OpenAPI documentation.
 * This enables automatic generation of API documentation, making it easier
 * for developers to explore and test the endpoints.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Defines an OpenAPI bean that customizes API metadata.
     * This provides useful information like the API title, version, and description
     * in the generated Swagger documentation.
     *
     * @return an OpenAPI instance with customized metadata.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dispatch Load Balancer API") // Sets the API title.
                        .version("1.0") // Specifies the API version.
                        .description("API for optimizing dispatch of orders to vehicles")); // Short description of API functionality.
    }
}
