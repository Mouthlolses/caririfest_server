package com.caririfestserver.caririfest_api.tickets.model

import com.caririfestserver.caririfest_api.events.model.Event
import com.caririfestserver.caririfest_api.model.order.Order
import com.caririfestserver.caririfest_api.users.model.User
import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "tickets")
data class Ticket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**Pedido que originou o ingresso*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    /**Evento ao qual o ingresso pertence*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    val event: Event,

    /**Usuário dono do ingresso*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    /**Tipo de ingresso (inteira, VIP, etc)*/
    @Enumerated(EnumType.STRING)
    val ticketType: TicketType,

    /**Código único usado no QR Code*/
    //Na hora de criar > UUID.randomUUID()
    @Column(unique = true, nullable = false, updatable = false)
    val accessCode: UUID,

    /**Status do ingresso*/
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: TicketStatus,

    /**Momento em que o ingresso foi utilizado*/
    val usedAt: Instant? = null

)