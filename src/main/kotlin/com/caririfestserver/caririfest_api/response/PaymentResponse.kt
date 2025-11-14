package com.caririfestserver.caririfest_api.response

import com.caririfestserver.caririfest_api.model.paymentMethod.PaymentMethod
import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentResponse(
    val id: Long,
    val orderId: Long,
    val paymentMethod: PaymentMethod,
    val status: String,
    val amountPaid: BigDecimal,
    val dateConfirmation: LocalDateTime?
)
