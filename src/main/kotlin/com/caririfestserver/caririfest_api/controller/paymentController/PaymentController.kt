package com.caririfestserver.caririfest_api.controller.paymentController

import com.caririfestserver.caririfest_api.request.payment.PaymentRequest
import com.caririfestserver.caririfest_api.response.PaymentResponse
import com.caririfestserver.caririfest_api.service.PaymentService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping
    fun createPayment(@Valid @RequestBody request: PaymentRequest): PaymentResponse {
        return paymentService.createPayment(request)
    }

    @GetMapping("/{id}")
    fun getPaymentById(
        @PathVariable id: Long,
    ): PaymentResponse {
        return paymentService.getPaymentById(id)
    }

    @GetMapping
    fun getAllPayments(): List<PaymentResponse> {
        return paymentService.getAllPayments()
    }
}