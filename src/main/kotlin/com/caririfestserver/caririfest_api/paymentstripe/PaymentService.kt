package com.caririfestserver.caririfest_api.paymentstripe

import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import org.springframework.stereotype.Service

@Service
class PaymentService {


    fun createPaymentIntent(payment: CreatePayment): PaymentIntent {
        val totalAmount = calculateAmount(payment)

        val params = PaymentIntentCreateParams.builder()
            .setAmount(totalAmount)
            .setCurrency("brl")
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                    .setEnabled(true)
                    .build()
            )
            .build()

        return PaymentIntent.create(params)
    }



    private fun calculateAmount(payment: CreatePayment): Long {
        return payment.items.sumOf { it.amount }
    }
}