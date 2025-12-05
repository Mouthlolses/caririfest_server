package com.caririfestserver.caririfest_api.model.admin

import com.caririfestserver.caririfest_api.annotations.NotEmpty
import com.caririfestserver.caririfest_api.model.Event
import jakarta.persistence.*

@Entity
@Table(name = "admins")
data class Admin(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotEmpty val adminName: String,

    @field:NotEmpty val adminLastName: String,

    @field:NotEmpty val docAdmin: String,

    @field:NotEmpty val adminEmail: String,

    @field:NotEmpty val adminEmailConfirm: String,

    @field:NotEmpty val password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    val event: Event? = null

)
