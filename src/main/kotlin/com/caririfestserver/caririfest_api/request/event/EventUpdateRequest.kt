package com.caririfestserver.caririfest_api.request.event

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class EventUpdateRequest(
    val title: String? = null,
    val local: String? = null,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val price: BigDecimal? = null,
    val ticketsAvailable: Int? = null
)