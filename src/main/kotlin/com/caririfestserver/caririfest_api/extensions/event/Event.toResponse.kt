package com.caririfestserver.caririfest_api.extensions.event

import com.caririfestserver.caririfest_api.model.Event
import com.caririfestserver.caririfest_api.response.EventResponse


fun Event.toResponse(): EventResponse {
    return EventResponse(
        id = this.id!!,
        title = this.title,
        local = this.local,
        date = this.date,
        time = this.time,
        price = this.price,
        ticketsAvailable = this.ticketsAvailable,
    )
}