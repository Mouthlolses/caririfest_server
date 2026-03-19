package com.caririfestserver.caririfest_api.payments.checkout.dto

//Esse checkoutUrl vem da Stripe
data class CheckoutResponse(
    val checkoutUrl: String
)