package com.caririfestserver.caririfest_api.request.admin.recovery

data class ResetPasswordRequest(
    val token: String,
    val newPassword: String
)
