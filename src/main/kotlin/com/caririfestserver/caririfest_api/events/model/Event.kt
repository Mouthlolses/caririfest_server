package com.caririfestserver.caririfest_api.events.model

import com.caririfestserver.caririfest_api.admin.model.Admin
import com.caririfestserver.caririfest_api.events.model.category.Category
import com.caririfestserver.caririfest_api.order.model.Order
import com.caririfestserver.caririfest_api.tickets.model.Ticket
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(
    name = "events",
    indexes = [
        Index(name = "idx_event_date", columnList = "date"),
        Index(name = "idx_event_category", columnList = "category_id"),
        Index(name = "idx_event_status", columnList = "status")
    ]
)
data class Event(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 150)
    var title: String,

    @Column(nullable = false, length = 2000)
    var description: String,

    @Column(nullable = false)
    var bannerUrl: String,

    @Column(nullable = false, length = 150)
    var locationName: String,

    @Column(length = 255)
    var address: String? = null,

    @Column(nullable = false)
    var date: LocalDate,

    @Column(nullable = false)
    var time: LocalTime,

    @Column(nullable = false, precision = 10, scale = 2)
    var price: BigDecimal,

    @Column(nullable = false)
    var totalTickets: Int,

    @Column(nullable = false)
    var ticketsAvailable: Int,

    /** Categoria do evento */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    val category: Category,

    /** Organizador do evento */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id")
    val admin: Admin,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: EventStatus = EventStatus.ACTIVE,

    @Column(nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    var updatedAt: Instant? = null,

    /** Pedidos feitos para esse evento */
    @OneToMany(mappedBy = "event")
    val orders: MutableList<Order> = mutableListOf(),

    /** Ingressos desse evento */
    @OneToMany(mappedBy = "event")
    val tickets: MutableList<Ticket> = mutableListOf()
)