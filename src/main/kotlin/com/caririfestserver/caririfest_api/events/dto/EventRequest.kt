package com.caririfestserver.caririfest_api.events.dto

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

data class EventRequest(

    val title: String,

    val description: String,

    val bannerUrl: String,

    val locationName: String,

    val address: String?,

    val date: LocalDate,

    val time: LocalTime,

    val price: BigDecimal,

    val totalTickets: Int,

    val ticketsAvailable: Int,

    val categoryName: String,

    val status: String,

    val createdAt: Instant
)
