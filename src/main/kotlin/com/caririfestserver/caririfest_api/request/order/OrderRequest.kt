package com.caririfestserver.caririfest_api.request.order

import com.caririfestserver.caririfest_api.model.statusPayment.StatusPayment
import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderRequest(
    val clientId: Long,
    val eventId: Long,
    val dateHourOrder: LocalDateTime,
    val totalPrice: BigDecimal,
    val statusPayment: StatusPayment
)