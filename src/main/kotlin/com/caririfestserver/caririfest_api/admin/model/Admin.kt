package com.caririfestserver.caririfest_api.admin.model

import com.caririfestserver.caririfest_api.events.model.Event
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(
    name = "admins",
    indexes = [
        Index(name = "idx_admin_email", columnList = "email")
    ]
)
data class Admin(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false, length = 150, unique = true)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: AdminRole,

    @Column(nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @OneToMany(mappedBy = "admin")
    val events: List<Event> = emptyList()
)