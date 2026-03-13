package com.caririfestserver.caririfest_api.users.model

import com.caririfestserver.caririfest_api.annotations.NotEmpty
import com.caririfestserver.caririfest_api.model.order.Order
import com.caririfestserver.caririfest_api.tickets.model.Ticket
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotEmpty
    val name: String,

    @field:NotEmpty
    val lastName: String,

    @field:NotEmpty
    val doc: String,

    @field:NotEmpty
    val email: String,

    val whatsapp: String? = null,

    val password: String? = null,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @OneToMany(mappedBy = "client")
    val orders: List<Order> = emptyList(),

    @OneToMany(mappedBy = "client")
    val tickets: List<Ticket> = emptyList()
)
