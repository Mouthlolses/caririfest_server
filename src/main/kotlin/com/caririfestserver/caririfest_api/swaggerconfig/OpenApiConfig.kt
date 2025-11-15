package com.caririfestserver.caririfest_api.swaggerconfig

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun CustomOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("CarirfFest APi")
                    .version("1.0")
                    .description("Documentação da API do Caririfest")
            )
    }
}