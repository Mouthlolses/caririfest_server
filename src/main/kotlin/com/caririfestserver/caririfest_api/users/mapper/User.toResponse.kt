package com.caririfestserver.caririfest_api.users.mapper

import com.caririfestserver.caririfest_api.users.dto.UserResponse
import com.caririfestserver.caririfest_api.users.model.User

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id!!,
        name = this.name,
        lastName = this.lastName,
        email = this.email,
        role = this.role.name
    )
}