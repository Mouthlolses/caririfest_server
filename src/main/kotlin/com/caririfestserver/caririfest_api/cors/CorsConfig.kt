package com.caririfestserver.caririfest_api.cors

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {

        val config = CorsConfiguration()

        config.allowCredentials = true
        config.allowedOrigins = listOf(
            "http://10.0.0.161:8080", // Celular físico no WiFi
            "http://10.0.2.2:8080"    // Emulador Android
        )
        config.allowedHeaders = listOf("*")
        config.allowedMethods = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)

        return source
    }
}