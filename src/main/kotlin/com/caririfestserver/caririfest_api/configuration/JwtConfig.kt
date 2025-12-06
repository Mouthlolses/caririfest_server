package com.caririfestserver.caririfest_api.configuration

import com.caririfestserver.caririfest_api.service.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Bean
    fun jwtService(): JwtService {
        return JwtService(secretKey)
    }
}