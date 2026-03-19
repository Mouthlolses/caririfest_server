package com.caririfestserver.caririfest_api.payments.webhook

import com.caririfestserver.caririfest_api.payments.service.PaymentService
import jakarta.servlet.http.HttpServletRequest

interface WebhookService {

    fun processStripeEvent(request: HttpServletRequest)
}


class WebhookServiceImpl(
    private val paymentService: PaymentService
) : WebhookService {

    override fun processStripeEvent(request: HttpServletRequest) {

        val payload = request.inputStream.bufferedReader().readText()
        val sigHeader = request.getHeader("Stripe-Signature")

        // validar evento da Stripe aqui

        val eventType = "checkout.session.completed" // exemplo

        val sessionId = "session_id_mock"

        when (eventType) {
            "checkout.session.completed" -> {
                paymentService.handlePaymentSuccess(sessionId)
            }

            "checkout.session.expired",
            "payment_intent.payment_failed" -> {
                paymentService.handlePaymentFailure(sessionId)
            }
        }
    }

}