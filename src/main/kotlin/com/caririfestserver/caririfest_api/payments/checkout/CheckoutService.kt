package com.caririfestserver.caririfest_api.payments.checkout

import com.caririfestserver.caririfest_api.payments.checkout.dto.CheckoutRequest
import com.caririfestserver.caririfest_api.payments.checkout.dto.CheckoutResponse
import com.caririfestserver.caririfest_api.tickets.service.TicketService
import org.springframework.stereotype.Service


//validar tickets
//→ reservar
//→ criar/buscar customer
//→ criar order
//→ criar payment
//→ criar sessão Stripe

// (ORQUESTRADOR)

//junta tudo
//
//define o fluxo

interface CheckoutService {

    fun startCheckout(request: CheckoutRequest): CheckoutResponse


}


@Service
class CheckoutServiceImpl(
    private val ticketService: TicketService,
    private val customerService: CustomerService,
    private val orderService: OrderService,
    private val paymentService: PaymentService
) : CheckoutService {

    // 1. validar disponibilidade
    ticketService.validateAvailability(request.eventId, request.quantity)

    // 2. reservar ingressos
    val reservation = ticketService.reserveTickets(request.eventId, request.quantity)

    // 3. criar ou buscar customer
    val customer = customerService.getOrCreate(
        request.customerName,
        request.customerEmail
    )

    // 4. criar order
    val order = orderService.createOrder(customer, reservation)

    // 5. criar payment
    val payment = paymentService.createPayment(order, customer)

    // 6. criar sessão Stripe
    val checkoutUrl = paymentService.createStripeSession(order, payment)

    return CheckoutResponse(checkoutUrl)
}