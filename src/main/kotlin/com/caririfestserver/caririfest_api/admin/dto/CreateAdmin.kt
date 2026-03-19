package com.caririfestserver.caririfest_api.admin.dto

import com.caririfestserver.caririfest_api.admin.model.AdminRole

data class CreateAdmin(
    val name: String,
    val email: String,
    val password: String,
    val role: AdminRole,
)
