package com.caririfestserver.caririfest_api.payments.checkout

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


//Só recebe request e retorna response

@RestController
@RequestMapping("/checkout")
class CheckoutController(
    private val checkoutService: CheckoutService
) {

    @PostMapping
    fun checkout(@RequestBody request: CheckoutRequest): CheckoutResponse {
        return checkoutService.startCheckout(request)
    }
}