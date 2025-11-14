package com.caririfestserver.caririfest_api.request.event

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class EventRequest(
    val title: String,
    val local: String,
    val date: LocalDate,
    val time: LocalTime,
    val price: BigDecimal,
    val ticketsAvailable: Int
)