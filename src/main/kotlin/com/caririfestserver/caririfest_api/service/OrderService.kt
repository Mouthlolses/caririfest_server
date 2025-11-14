package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.order.toResponse
import com.caririfestserver.caririfest_api.model.Order
import com.caririfestserver.caririfest_api.repository.ClientRepository
import com.caririfestserver.caririfest_api.repository.EventRepository
import com.caririfestserver.caririfest_api.repository.OrderRepository
import com.caririfestserver.caririfest_api.request.order.OrderRequest
import com.caririfestserver.caririfest_api.request.order.UpdateOrderStatusRequest
import com.caririfestserver.caririfest_api.response.OrderResponse
import org.springframework.stereotype.Service
import java.time.LocalDateTime


/**
 * Order é a espinha dorsal do sistema.
 *
 * Criar pedido
 *
 * Validar cliente
 *
 * Validar evento
 *
 * Calcular total
 *
 * Associar pagamento
 *
 * Atualizar status do pedido
 *
 * Garantir atomicidade do processo
 * */

@Service
class OrderService(
    private val repository: OrderRepository,
    private val clientRepository: ClientRepository,
    private val eventRepository: EventRepository
) {

    fun createOrder(request: OrderRequest): OrderResponse {

        val client = clientRepository.findById(request.clientId)
            .orElseThrow { NoSuchElementException("Cliente não encontrado id=${request.clientId}") }

        val event = eventRepository.findById(request.eventId)
            .orElseThrow { NoSuchElementException("Evento não encontrado id=${request.eventId}") }

        val order = Order(
            client = client,
            event = event,
            dateHourOrder = LocalDateTime.now(),
            totalPrice = request.totalPrice,
            statusPayment = request.statusPayment
        )

        val saved = repository.save(order)
        return saved.toResponse()
    }

    fun getOrderById(id: Long): OrderResponse {
        val order = repository.findById(id)
            .orElseThrow { NoSuchElementException("Pedido não encontrado id=$id") }

        return order.toResponse()
    }

    fun getAllOrders(): List<OrderResponse> {
        return repository.findAll()
            .map { it.toResponse() }
    }

    fun updateStatusOrder(id: Long, request: UpdateOrderStatusRequest): OrderResponse {
        val order = repository.findById(id)
            .orElseThrow { NoSuchElementException("Pedido não encontrado id=$id") }

        order.statusPayment = request.statusPayment

        val update = repository.save(order)

        return update.toResponse()
    }
}