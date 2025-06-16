package com.pdd.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("!prod")
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("${openapi.service.title}") String title,
                           @Value("${openapi.service.version}") String version,
                           @Value("${openapi.service.description}") String description,
                           @Value("${openapi.service.serverUrl}") String serverUrl,
                           @Value("${openapi.service.serverName}") String serverName
    ) {
        System.out.println("title" + title);
        System.out.println("version" + version);
        System.out.println("description" + description);
        System.out.println("serverUrl" + serverUrl);
        System.out.println("serverName" + serverName);
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(description)
                .termsOfService("termsOfService")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(new Server().url(serverUrl).description(serverName)))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearerAuth",
                                        new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")))
                .security(List.of(new SecurityRequirement().addList("bearerAuth")));
    }


    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("api-service")
                .packagesToScan("com.pdd.controller")
                .build();
    }
}
