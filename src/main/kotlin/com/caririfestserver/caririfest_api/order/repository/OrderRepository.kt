package com.caririfestserver.caririfest_api.order.repository

import com.caririfestserver.caririfest_api.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderRepository : JpaRepository<Order, Long> {

    fun findByCustomerId(customerId: UUID): List<Order>

    fun findByEventId(eventId: Long): List<Order>
}