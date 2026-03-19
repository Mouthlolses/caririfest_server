package com.caririfestserver.caririfest_api.payments.model

import com.caririfestserver.caririfest_api.customers.model.Customer
import com.caririfestserver.caririfest_api.order.model.Order
import jakarta.persistence.*
import java.time.Instant

//Representa a transação financeira

@Entity
@Table(name = "payments")
data class Payment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val stripeSessionId: String,

    val stripePaymentIntentId: String? = null,

    @Enumerated(EnumType.STRING)
    var status: PaymentStatus,

    val amount: Long,

    val createdAt: Instant = Instant.now(),

    var updatedAt: Instant? = null,

    @ManyToOne
    val customer: Customer,

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order
)