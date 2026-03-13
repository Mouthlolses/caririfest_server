package com.caririfestserver.caririfest_api.tickets.dto

data class TicketResponse(
    val id: Long,
    val eventName: String,
    val eventDate: String,
    val accessCode: String
)
