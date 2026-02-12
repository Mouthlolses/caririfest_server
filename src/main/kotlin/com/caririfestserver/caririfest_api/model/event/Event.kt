package com.caririfestserver.caririfest_api.model.event

import com.caririfestserver.caririfest_api.model.Order
import com.caririfestserver.caririfest_api.model.Ticket
import com.caririfestserver.caririfest_api.model.event.category.Category
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
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

    // banner principal
    @Column(nullable = false)
    var bannerUrl: String,

    @Column(nullable = false, length = 150)
    var locationName: String,

    // depois você pode evoluir pra lat/long
    @Column(length = 255)
    var address: String? = null,

    @Column(nullable = false)
    var date: LocalDate,

    @Column(nullable = false)
    var time: LocalTime,

    @Column(nullable = false, precision = 10, scale = 2)
    var price: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var totalTickets: Int,

    @Column(nullable = false)
    var ticketsAvailable: Int,

    // 👇 categoria obrigatória
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    // controle de ciclo de vida
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: EventStatus = EventStatus.ACTIVE,

    // auditoria (MUITO profissional ter isso)
    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime? = null,

    // relações
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    val orders: MutableList<Order> = mutableListOf(),

    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    val tickets: MutableList<Ticket> = mutableListOf()
)