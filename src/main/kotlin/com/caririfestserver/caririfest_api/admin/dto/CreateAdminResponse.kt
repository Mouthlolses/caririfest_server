package com.caririfestserver.caririfest_api.admin.dto

data class CreateAdminResponse(
    val name: String,
    val email: String,
    val password: String,
    val role: String
)
