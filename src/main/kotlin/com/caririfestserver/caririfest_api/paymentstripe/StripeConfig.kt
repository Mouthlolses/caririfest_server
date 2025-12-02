package com.caririfestserver.caririfest_api.paymentstripe

import com.stripe.Stripe
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration


@Configuration
class StripeConfig {

    @Value("\${stripe.secret}")
    lateinit var secret: String

    @PostConstruct
    fun init() {
        Stripe.apiKey = secret
    }
}