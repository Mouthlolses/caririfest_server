package com.caririfestserver.caririfest_api.users.dto

data class UserResponse(
    val id: Long,
    val name: String,
    val lastName: String,
    val email: String,
    val role: String
)
