package com.caririfestserver.caririfest_api.events.mapper

import com.caririfestserver.caririfest_api.events.dto.EventResponse
import com.caririfestserver.caririfest_api.events.model.Event

fun Event.toResponse(): EventResponse {
    return EventResponse(
        id = requireNotNull(this.id),
        title = this.title,
        description = this.description,
        bannerUrl = this.bannerUrl,
        locationName = this.locationName,
        address = this.address,
        date = this.date,
        time = this.time,
        price = this.price,
        totalTickets = this.totalTickets,
        ticketsAvailable = this.ticketsAvailable,
        categoryName = this.category.name,
        status = this.status.name,
        createdAt = this.createdAt
    )
}