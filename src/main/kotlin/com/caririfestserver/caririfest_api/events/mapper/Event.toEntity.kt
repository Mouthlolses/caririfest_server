package com.caririfestserver.caririfest_api.events.mapper

import com.caririfestserver.caririfest_api.events.dto.EventRequest
import com.caririfestserver.caririfest_api.events.model.Event


fun Event.toEntity(): EventRequest {
    return EventRequest(
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