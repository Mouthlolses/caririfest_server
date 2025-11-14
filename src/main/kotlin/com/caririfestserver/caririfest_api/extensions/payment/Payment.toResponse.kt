package com.caririfestserver.caririfest_api.extensions.payment

import com.caririfestserver.caririfest_api.model.Payment
import com.caririfestserver.caririfest_api.response.PaymentResponse

fun Payment.toResponse(): PaymentResponse {
    return PaymentResponse(
        id = this.id!!,
        orderId = this.order.id!!,
        paymentMethod = this.paymentMethod,
        status = this.gatewayTransactionId,
        amountPaid = this.amountPaid,
        dateConfirmation = this.dateConfirmation,
    )
}