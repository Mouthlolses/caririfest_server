package com.caririfestserver.caririfest_api.admin.dto

data class AuthRequest(
    val email: String,
    val password: String
)