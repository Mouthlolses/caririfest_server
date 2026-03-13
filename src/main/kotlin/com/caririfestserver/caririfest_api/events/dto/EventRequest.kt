package com.caririfestserver.caririfest_api.events.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class EventRequest(

    @field:NotBlank
    @field:Size(max = 150)
    val title: String,

    @field:NotBlank
    @field:Size(max = 2000)
    val description: String,

    @field:NotBlank
    val bannerUrl: String,

    @field:NotBlank
    val locationName: String,

    val address: String? = null,

    @field:NotNull
    val date: LocalDate,

    @field:NotNull
    val time: LocalTime,

    @field:DecimalMin("0.0")
    val price: BigDecimal,

    @field:Min(1)
    val totalTickets: Int,

    @field:NotNull
    val categoryId: Long
)
