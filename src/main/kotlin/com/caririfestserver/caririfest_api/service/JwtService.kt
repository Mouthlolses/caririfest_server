package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.model.organizer.Organizer
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(private val secretKey: String) {

    fun generateToken(admin: Organizer): String {
        return Jwts.builder()
            .setSubject(admin.id.toString())
            .claim("email", admin.adminEmail)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
            .signWith(SignatureAlgorithm.HS256, secretKey.toByteArray())
            .compact()
    }
}