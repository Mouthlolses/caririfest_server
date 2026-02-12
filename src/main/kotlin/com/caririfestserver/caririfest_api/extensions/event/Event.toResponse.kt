package com.caririfestserver.caririfest_api.extensions.event

import com.caririfestserver.caririfest_api.model.event.Event
import com.caririfestserver.caririfest_api.response.EventResponse


fun Event.toResponse(): EventResponse {
    return EventResponse(
        id = this.id!!,
        title = this.title,
        description = this.description,
        bannerUrl = this.bannerUrl,
        locationName = this.locationName,
        address = this.address,
        date = this.date,
        time = this.time,
        price = this.price,
        ticketsAvailable = this.ticketsAvailable,
        category = this.category,
        status = this.status
    )
}