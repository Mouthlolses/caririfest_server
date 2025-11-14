package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.payment.toResponse
import com.caririfestserver.caririfest_api.model.Payment
import com.caririfestserver.caririfest_api.repository.OrderRepository
import com.caririfestserver.caririfest_api.repository.PaymentRepository
import com.caririfestserver.caririfest_api.request.payment.PaymentRequest
import com.caririfestserver.caririfest_api.response.PaymentResponse
import org.springframework.stereotype.Service

/**
 * Processar pagamento
 *
 * Receber callback do gateway
 *
 * Atualizar status
 *
 * Validar valor recebido
 *
 * Criar parcelas (se houver)
 * */

@Service
class PaymentService(
    private val repository: PaymentRepository,
    private val orderRepository: OrderRepository
) {

    fun createPayment(request: PaymentRequest): PaymentResponse {
        val order = orderRepository.findById(request.orderId)
            .orElseThrow { NoSuchElementException("Pedido não encontrado id=\${request.orderId}") }

        val payment = Payment(
            order = order,
            gatewayTransactionId = "PENDING",   // será atualizado após callback do gateway
            paymentMethod = request.paymentMethod,
            totalInstallments = request.totalInstallments,
            installments = 1,
            amountPaid = request.amountPaid
        )

        val saved = repository.save(payment)
        return saved.toResponse()
    }

    fun getPaymentById(id: Long): PaymentResponse {
        val payment = repository.findById(id)
            .orElseThrow { NoSuchElementException("Pagamento não encontrado id=$id") }
        return payment.toResponse()
    }

    fun getAllPayments(): List<PaymentResponse> {
        return repository.findAll()
            .map { it.toResponse() }
    }
}