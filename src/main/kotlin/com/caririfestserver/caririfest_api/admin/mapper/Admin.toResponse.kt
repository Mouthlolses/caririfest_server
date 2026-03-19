package com.caririfestserver.caririfest_api.admin.mapper

import com.caririfestserver.caririfest_api.admin.dto.CreateAdminResponse
import com.caririfestserver.caririfest_api.admin.model.Admin

fun Admin.toResponse(): CreateAdminResponse {
    return CreateAdminResponse(
        name = this.name,
        email = this.email,
        password = this.password,
        role = this.role.name
    )
}