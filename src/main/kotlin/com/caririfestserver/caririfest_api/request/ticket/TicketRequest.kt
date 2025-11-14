package com.caririfestserver.caririfest_api.request.ticket

data class TicketRequest(
    val orderId: Long,
    val eventId: Long,
    val clientId: Long,
)