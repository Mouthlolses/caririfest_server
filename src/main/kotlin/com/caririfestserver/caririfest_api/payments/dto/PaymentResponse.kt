package com.caririfestserver.caririfest_api.payments.dto

data class PaymentResponse(
    val id: Long,
    val status: String,
    val amount: Long,
    val orderId: Long
)