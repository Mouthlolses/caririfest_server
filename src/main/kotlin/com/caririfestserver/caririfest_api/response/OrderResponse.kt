package com.caririfestserver.caririfest_api.response

import com.caririfestserver.caririfest_api.model.statusPayment.StatusPayment
import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    val client: ClientResponse,
    val event: EventResponse,
    val dateHourOrder: LocalDateTime,
    val totalPrice: BigDecimal,
    val statusPayment: StatusPayment
)
