package com.caririfestserver.caririfest_api.customers.dto

data class CreateUserRequest(
    val name: String,
    val lastName: String,
    val doc: String,
    val email: String,
    val whatsapp: String?,
    val password: String?
)
