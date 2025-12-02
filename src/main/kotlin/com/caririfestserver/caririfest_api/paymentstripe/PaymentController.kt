package com.caririfestserver.caririfest_api.paymentstripe

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class PaymentController(
    private val service: PaymentService,
) {

    @PostMapping("/create-payment-intent")
    fun createPaymentIntent(@Valid @RequestBody body: CreatePayment): CreatePaymentResponse {
        val paymentIntent = service.createPaymentIntent(body)
        return CreatePaymentResponse(paymentIntent.clientSecret)
    }

}