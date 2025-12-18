package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.exceptions.domainExceptions.InvalidTokenException
import com.caririfestserver.caririfest_api.exceptions.domainExceptions.TokenExpiredException
import com.caririfestserver.caririfest_api.model.admin.PasswordResetToken
import com.caririfestserver.caririfest_api.repository.AdminRepository
import com.caririfestserver.caririfest_api.repository.PasswordResetTokenRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class PasswordRecoveryService(
    private val adminRepository: AdminRepository,
    private val tokenRepository: PasswordResetTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService
) {

    fun requestReset(email: String) {

        val admin = adminRepository.findByAdminEmail(email)
            ?: return

        val rawToken = UUID.randomUUID().toString()

        val token = PasswordResetToken(
            adminId = admin.id,
            tokenHash = passwordEncoder.encode(rawToken),
            expiresAt = LocalDateTime.now().plusMinutes(30),
            used = false
        )

        tokenRepository.save(token)

        val link = "https://seuapp.com/reset-password?token=$rawToken"

        try {
            emailService.sendPasswordResetEmail(
                to = admin.adminEmail,
                link = link
            )
        } catch (ex: Exception) {
            // LOGA O ERRO (importantíssimo)
            println("Erro ao enviar email de recuperação: ${ex.message}")
            ex.printStackTrace()

        }
    }

    fun resetPassword(token: String, newPassword: String) {

        val validToken = tokenRepository.findByUsedFalse()
            .firstOrNull { passwordEncoder.matches(token, it.tokenHash) }
            ?: throw InvalidTokenException()

        if (validToken.expiresAt.isBefore(LocalDateTime.now())) {
            throw TokenExpiredException()
        }

        val admin = adminRepository.findById(validToken.adminId)
            .orElseThrow { IllegalStateException("Admin não encontrado") }

        admin.password = passwordEncoder.encode(newPassword)
        adminRepository.save(admin)

        validToken.used = true
        tokenRepository.save(validToken)
    }

}