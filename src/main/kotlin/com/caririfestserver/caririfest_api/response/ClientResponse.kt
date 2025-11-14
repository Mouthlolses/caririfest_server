package com.caririfestserver.caririfest_api.response

data class ClientResponse(
    val id: Long,
    val clientName: String,
    val clientEmail: String,
    val clientWhatsApp: String
)
