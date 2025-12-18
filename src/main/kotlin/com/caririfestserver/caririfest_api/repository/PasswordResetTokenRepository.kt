package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.admin.PasswordResetToken
import org.springframework.data.jpa.repository.JpaRepository

interface PasswordResetTokenRepository : JpaRepository<PasswordResetToken, Long> {


    fun findByUsedFalse(): List<PasswordResetToken>

}