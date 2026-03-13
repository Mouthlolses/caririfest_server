package com.caririfestserver.caririfest_api.events.model.category

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "categories",
    indexes = [
        Index(name = "idx_category_name", columnList = "name")
    ]
)
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 80)
    val name: String,

    // útil pro app mobile
    @Column(length = 120)
    val icon: String? = null,

    // HEX -> #FF5733
    @Column(length = 7)
    val color: String? = null,

    @Column(length = 255)
    val description: String? = null,

    // evita apagar categoria que já possui eventos
    @Column(nullable = false)
    val active: Boolean = true,

    @Column(nullable = false, updatable = false)
    val createdAt: Instant = Instant.now()
)
