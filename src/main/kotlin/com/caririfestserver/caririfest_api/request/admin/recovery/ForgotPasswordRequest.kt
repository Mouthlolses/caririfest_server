package com.caririfestserver.caririfest_api.request.admin.recovery

import jakarta.validation.constraints.NotBlank

data class ForgotPasswordRequest(

    @field:NotBlank
    val email: String
)
