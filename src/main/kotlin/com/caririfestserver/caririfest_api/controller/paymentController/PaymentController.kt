package com.caririfestserver.caririfest_api.controller.paymentController

import com.caririfestserver.caririfest_api.request.payment.PaymentRequest
import com.caririfestserver.caririfest_api.response.PaymentResponse
import com.caririfestserver.caririfest_api.service.PaymentService
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(summary = "Cria pagamento(a partir de um pedido)", description = "Controlado pela Api(Gateway)")
    fun createPayment(@Valid @RequestBody request: PaymentRequest): PaymentResponse {
        return paymentService.createPayment(request)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista pagamento de determinado id(cliente)")
    fun getPaymentById(
        @PathVariable id: Long,
    ): PaymentResponse {
        return paymentService.getPaymentById(id)
    }

    @GetMapping
    @Operation(summary = "Lista todos os pagamentos de um cliente")
    fun getAllPayments(): List<PaymentResponse> {
        return paymentService.getAllPayments()
    }
}