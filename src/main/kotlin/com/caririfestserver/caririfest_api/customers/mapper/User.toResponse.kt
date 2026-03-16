package com.caririfestserver.caririfest_api.customers.mapper

import com.caririfestserver.caririfest_api.customers.dto.UserResponse
import com.caririfestserver.caririfest_api.customers.model.User

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id!!,
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        role = this.role.name
    )
}