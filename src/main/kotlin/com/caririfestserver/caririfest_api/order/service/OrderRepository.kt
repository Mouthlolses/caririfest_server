package com.caririfestserver.caririfest_api.order.service

import com.caririfestserver.caririfest_api.order.model.Order
import com.caririfestserver.caririfest_api.order.repository.OrderRepository
import org.springframework.stereotype.Service

import java.util.UUID


//
//cria order (PENDING)
//
//atualiza status

interface OrderService {

    fun create(order: Order): Order

    fun findByCustomer(customerId: UUID): List<Order>

    fun findByEvent(eventId: Long): List<Order>

}


@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
) : OrderService {

    override fun create(order: Order): Order {

        val orders = orderRepository.findByEventId(order.event.id!!)
        val totalTickets = orders.sumOf { it.tickets.size }

        return orderRepository.save(order)
    }

    override fun findByCustomer(customerId: UUID): List<Order> {
        return orderRepository.findByCustomerId(customerId)
    }

    override fun findByEvent(eventId: Long): List<Order> {
        return orderRepository.findByEventId(eventId)
    }

}