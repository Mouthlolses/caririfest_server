package com.caririfestserver.caririfest_api.model.admin


import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "password_reset_token")
data class PasswordResetToken(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    val adminId: Long,

    @Column(nullable = false)
    val tokenHash: String,

    @Column(nullable = false)
    val expiresAt: LocalDateTime,

    @Column(nullable = false)
    var used: Boolean = false,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)