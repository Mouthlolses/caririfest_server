package com.caririfestserver.caririfest_api.extensions.ticket

import com.caririfestserver.caririfest_api.model.Ticket
import com.caririfestserver.caririfest_api.response.TicketResponse

fun Ticket.toResponse(): TicketResponse {
    return TicketResponse(
        id = this.id!!,
        eventId = this.event.id!!,
        clientId = this.client.id!!,
        orderId = this.order.id!!,
        accessCode = this.accessCode,
        useStatus = this.useStatus,
        dataUso = this.dataUso
    )
}