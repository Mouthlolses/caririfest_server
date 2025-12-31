package com.caririfestserver.caririfest_api.request.admin

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AdminRequest(

    @field:NotBlank
    @field:Size(min = 2, max = 50)
    val adminName: String,

    @field:NotBlank
    @field:Size(min = 2, max = 50)
    val adminLastName: String,

    @field:NotBlank
    @field:Size(min = 2, max = 30)
    @field:Pattern(regexp = "\\d+", message = "Deve conter apenas números")
    val docAdmin: String,

    @field:NotBlank
    @field:Email
    val adminEmail: String,

    @field:NotBlank
    @field:Email
    val adminEmailConfirm: String,

    @field:NotBlank
    @field:Size(min = 6, max = 100)
    val password: String,

    val eventId: Long? = null
)
