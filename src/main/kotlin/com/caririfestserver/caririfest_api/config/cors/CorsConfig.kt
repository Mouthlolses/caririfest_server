package com.caririfestserver.caririfest_api.config.cors

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {

        // Configuração para o app mobile (emulador e físico) - acesso completo
        val mobileConfig = CorsConfiguration()
        mobileConfig.allowCredentials = true
        mobileConfig.allowedOrigins = listOf(
            "http://10.0.0.161:8080", // Celular físico no WiFi
            "http://10.0.2.2:8080"    // Emulador Android
        )
        mobileConfig.allowedHeaders = listOf("*")
        mobileConfig.allowedMethods = listOf("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", mobileConfig)

        //----------------------------------------------------//

        // Configuração para web - só alguns métodos permitidos
        val webConfig = CorsConfiguration()
        webConfig.allowCredentials = true
        webConfig.allowedOrigins = listOf(
            "https://meusite.com"
        )
        webConfig.allowedHeaders = listOf("*")
        webConfig.allowedMethods = listOf("GET") // Somente GET
        source.registerCorsConfiguration("/**", webConfig)


        return source
    }
}