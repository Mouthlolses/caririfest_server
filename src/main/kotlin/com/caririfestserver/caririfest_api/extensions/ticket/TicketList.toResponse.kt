package com.caririfestserver.caririfest_api.extensions.ticket

import com.caririfestserver.caririfest_api.model.Ticket
import com.caririfestserver.caririfest_api.response.TicketResponse

fun List<Ticket>.toResponse(): List<TicketResponse> {
    return this.map { ticket ->
        TicketResponse(
            id = ticket.id!!,
            eventId = ticket.event.id!!,
            clientId = ticket.client.id!!,
            orderId = ticket.order.id!!,
            accessCode = ticket.accessCode,
            useStatus = ticket.useStatus,
            dataUso = ticket.dataUso
        )
    }
}