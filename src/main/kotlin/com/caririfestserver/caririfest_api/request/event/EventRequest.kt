package com.caririfestserver.caririfest_api.request.event

import com.caririfestserver.caririfest_api.model.Order
import com.caririfestserver.caririfest_api.model.Ticket
import com.caririfestserver.caririfest_api.model.event.EventStatus
import com.caririfestserver.caririfest_api.model.event.category.Category
import jakarta.validation.constraints.Min
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class EventRequest(
    val title: String,
    val description: String,
    val bannerUrl: String,
    val locationName: String,
    val address: String,
    val date: LocalDate,
    val time: LocalTime,
    val price: BigDecimal,

    @field:Min(0, message = "Quantidade de ingressos não pode ser negativa")
    val totalTickets: Int,

    val category: Category,
    val status: EventStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val orders: MutableList<Order>,
    val tickets: MutableList<Ticket>
)