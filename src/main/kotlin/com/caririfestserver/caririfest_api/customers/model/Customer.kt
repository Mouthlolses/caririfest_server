package com.caririfestserver.caririfest_api.customers.model

import com.caririfestserver.caririfest_api.order.model.Order
import com.caririfestserver.caririfest_api.tickets.model.Ticket
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "customers",
    indexes = [
        Index(name = "idx_customer_doc", columnList = "doc"),
        Index(name = "idx_customer_email", columnList = "email")
    ]
)
data class Customer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 50)
    val name: String,

    @Column(nullable = false, length = 50)
    val lastName: String,

    @Column(nullable = false, length = 11)
    val doc: String,

    @Column(nullable = false, length = 150)
    val email: String,

    @Column(length = 20)
    val phone: String? = null,

    @Column(nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @OneToMany(mappedBy = "customer")
    val orders: List<Order> = emptyList(),

    @OneToMany(mappedBy = "customer")
    val tickets: List<Ticket> = emptyList()
)