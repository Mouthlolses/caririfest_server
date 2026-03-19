package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.admin.model.Admin
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtService(
    @field:Value("\${jwt.secret}")
    private val secretKey: String,

    @field:Value("\${jwt.expiration}")
    private val expiration: Long
) {

    fun generateToken(admin: Admin): String {

        val keyBytes = Decoders.BASE64.decode(secretKey)

        return Jwts.builder()
            .setSubject(admin.id.toString())
            .claim("email", admin.email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(Keys.hmacShaKeyFor(keyBytes))
            .compact()
    }
}