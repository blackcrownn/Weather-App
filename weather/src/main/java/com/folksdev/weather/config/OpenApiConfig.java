package com.folksdev.weather.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customerOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("open weather service apı")
                        .version("1.0")
                        .description("""
                                this is an api proves weather report
                                for las 30 minutes based on city names
                                """
                        ));
    }
}
