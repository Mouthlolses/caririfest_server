package com.caririfestserver.caririfest_api.request.event

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class EventUpdateRequest(
    val title: String? = null,
    val description: String? = null,
    val bannerUrl: String? = null,
    val locationName: String? = null,
    val address: String? = null,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val price: BigDecimal? = null,
    val totalTickets: Int? = null
)