package com.caririfestserver.caririfest_api.payments.service

import com.caririfestserver.caririfest_api.order.model.Order
import com.caririfestserver.caririfest_api.order.model.OrderStatus
import com.caririfestserver.caririfest_api.order.repository.OrderRepository
import com.caririfestserver.caririfest_api.payments.model.Payment
import com.caririfestserver.caririfest_api.payments.model.PaymentStatus
import com.caririfestserver.caririfest_api.payments.repository.PaymentRepository
import com.stripe.model.Customer
import org.springframework.stereotype.Service


//cria payment
//
//chama Stripe
//
//atualiza via webhook

interface PaymentService {

    //Cria o Payment interno / status = PENDING / salva no banco / associa com order
    fun createPayment(order: Order, customer: Customer): Payment

    //Aqui você usa a Stripe / cria sessão / adiciona metadata (orderId, paymentId) / retorna URL
    fun createStripeSession(order: Order, payment: Payment): String

    //Chamado pelo webhook / busca payment pelo sessionId/ atualiza: payment = PAID order = CONFIRMED / gera tickets
    fun handlePaymentSuccess(sessionId: String)

    //Também via webhook / payment = FAILED / order = CANCELED / libera ingressos
    fun handlePaymentFailure(sessionId: String)
}


@Service
class PaymentServiceImpl(
    private val paymentRepository: PaymentRepository,
    private val orderRepository: OrderRepository
) : PaymentService {

    override fun createPayment(order: Order, customer: Customer): Payment {
        val payment = Payment(
            stripeSessionId = "",
            status = PaymentStatus.PENDING,
            amount = order.totalAmount,
            customer = customer,
            order = order
        )

        return paymentRepository.save(payment)
    }

    override fun createStripeSession(order: Order, payment: Payment): String {

        // chamada Stripe aqui

        val sessionId = "stripe_session_id_mock"
        val url = "https://checkout.stripe.com/pay/mock"

        // salva sessionId no payment
        val updatedPayment = payment.copy(stripeSessionId = sessionId)
        paymentRepository.save(updatedPayment)

        return url
    }

    override fun handlePaymentSuccess(sessionId: String) {
        val payment = paymentRepository.findByStripeSessionId(sessionId)
            ?: throw RuntimeException("Payment not found")

        if (payment.status == PaymentStatus.PAID) return

        payment.status = PaymentStatus.PAID
        paymentRepository.save(payment)

        val order = payment.order
        order.status = OrderStatus.CONFIRMED
        orderRepository.save(order)

        // gerar tickets aqui
    }

    override fun handlePaymentFailure(sessionId: String) {
        val payment = paymentRepository.findByStripeSessionId(sessionId)
            ?: throw RuntimeException("Payment not found")

        payment.status = PaymentStatus.FAILED
        paymentRepository.save(payment)

        val order = payment.order
        order.status = OrderStatus.CANCELED
        orderRepository.save(order)

        // liberar tickets aqui
    }


}