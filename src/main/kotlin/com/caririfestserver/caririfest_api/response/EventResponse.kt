package com.caririfestserver.caririfest_api.response

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class EventResponse(
    val id: Long,
    val title: String,
    val local: String,
    val date: LocalDate,
    val time: LocalTime,
    val price: BigDecimal,
    val ticketsAvailable: Int
)
