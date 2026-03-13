package com.caririfestserver.caririfest_api.tickets.mapper

import com.caririfestserver.caririfest_api.tickets.dto.TicketResponse
import com.caririfestserver.caririfest_api.tickets.model.Ticket

fun Ticket.toResponse(): TicketResponse {
    return TicketResponse(
        id = requireNotNull(this.id),
        eventName = this.event.title,
        eventDate = this.event.date.toString(),
        accessCode = this.accessCode.toString()
    )
}