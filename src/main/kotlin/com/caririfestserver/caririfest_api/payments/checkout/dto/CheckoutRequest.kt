package com.caririfestserver.caririfest_api.payments.checkout.dto

data class CheckoutRequest(
    val eventId: Long,
    val quantity: Int,
    val customerName: String,
    val customerEmail: String
)