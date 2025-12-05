package com.caririfestserver.caririfest_api.request.admin

data class AdminRequest(
    val adminName: String,
    val adminLastName: String,
    val docAdmin: String,
    val adminEmail: String,
    val adminEmailConfirm: String,
    val password: String,
    val eventId: Long? = null
)
