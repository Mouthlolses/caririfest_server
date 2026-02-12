package com.caririfestserver.caririfest_api.response

import com.caririfestserver.caririfest_api.model.event.EventStatus
import com.caririfestserver.caririfest_api.model.event.category.Category
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class EventResponse(
    val id: Long,
    val title: String,
    val description: String,
    val bannerUrl: String,
    val locationName: String,
    val address: String?,
    val date: LocalDate,
    val time: LocalTime,
    val price: BigDecimal,
    val ticketsAvailable: Int,
    val category: Category,
    val status: EventStatus
)
