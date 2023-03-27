package br.com.tex.restauranteapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerOpenAPIConfiguration {

    @Bean
    public OpenAPI documentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurante API")
                        .description("Está é uma API de um sistema de restaurante")
                        .version("V1.0.0"))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer"))
                .components(new Components()
                        .addSecuritySchemes("bearer", new SecurityScheme()
                                .name("bearer")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
