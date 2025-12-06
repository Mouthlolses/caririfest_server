package com.caririfestserver.caririfest_api.request.admin.login

import com.caririfestserver.caririfest_api.response.AdminResponse

data class LoginResponse(
    val token: String,
    val admin: AdminResponse
)
