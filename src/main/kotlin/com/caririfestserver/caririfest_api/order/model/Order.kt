package com.caririfestserver.caririfest_api.order.model

import com.caririfestserver.caririfest_api.customers.model.Customer
import com.caririfestserver.caririfest_api.events.model.Event
import com.caririfestserver.caririfest_api.tickets.model.Ticket
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "orders",
    indexes = [
        Index(name = "idx_order_customer", columnList = "customer_id"),
        Index(name = "idx_order_status", columnList = "status")
    ]
)
data class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    /** Cliente que realizou a compra */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,

    /** Evento do pedido */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: OrderStatus,

    /** valor total em centavos */
    @Column(nullable = false)
    val totalAmount: Long,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tickets: List<Ticket> = emptyList()
)