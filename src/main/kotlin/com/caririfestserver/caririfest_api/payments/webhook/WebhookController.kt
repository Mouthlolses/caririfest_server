package com.caririfestserver.caririfest_api.payments.webhook

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/webhook")
class WebhookController(
    private val webhookService: WebhookService
) {

    @PostMapping("/stripe")
    fun handleStripeWebhook(request: HttpServletRequest) {
        webhookService.processStripeEvent(request)
    }

}