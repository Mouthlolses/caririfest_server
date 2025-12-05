package com.caririfestserver.caririfest_api.response

data class AdminResponse(
    val id: Long,
    val adminName: String,
    val adminLastName: String,
    val docAdmin: String,
    val adminEmail: String,
    val eventId: Long? = null
)
