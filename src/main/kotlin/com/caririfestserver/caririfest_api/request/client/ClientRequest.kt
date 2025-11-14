package com.caririfestserver.caririfest_api.request.client

data class ClientRequest(
    val clientName: String,
    val clientLastName: String,
    val clientDoc: String,
    val clientEmail: String,
    val clientEmailConfirm: String,
    val clientWhatsApp: String
)