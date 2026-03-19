package com.caririfestserver.caririfest_api.admin.mapper

import com.caririfestserver.caririfest_api.admin.dto.CreateAdmin
import com.caririfestserver.caririfest_api.admin.model.Admin

fun CreateAdmin.toEntity(): Admin {
    return Admin(
        name = this.name,
        email = this.email,
        password = this.password,
        role = this.role
    )
}