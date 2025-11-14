package com.caririfestserver.caririfest_api.extensions.order

import com.caririfestserver.caririfest_api.extensions.client.toResponse
import com.caririfestserver.caririfest_api.extensions.event.toResponse
import com.caririfestserver.caririfest_api.model.Order
import com.caririfestserver.caririfest_api.response.OrderResponse

fun Order.toResponse(): OrderResponse {
    return OrderResponse(
        id = this.id!!,
        client = this.client.toResponse(),
        event = this.event.toResponse(),
        dateHourOrder = this.dateHourOrder,
        totalPrice = this.totalPrice,
        statusPayment = this.statusPayment,
    )
}