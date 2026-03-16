package com.caririfestserver.caririfest_api.tickets.model

import com.caririfestserver.caririfest_api.customers.model.Customer
import com.caririfestserver.caririfest_api.events.model.Event
import com.caririfestserver.caririfest_api.order.model.Order
import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "tickets")
data class Ticket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /** Pedido que originou o ingresso */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,

    /** Evento ao qual o ingresso pertence */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    /** Dono do ingresso */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,

    /** Tipo de ingresso (inteira, VIP, etc) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val ticketType: TicketType,

    /** Código único usado no QR Code */
    @Column(unique = true, nullable = false, updatable = false)
    val accessCode: UUID,

    /** Status do ingresso */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: TicketStatus,

    /** Momento em que o ingresso foi utilizado */
    val usedAt: Instant? = null
)