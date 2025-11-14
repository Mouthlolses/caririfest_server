package com.caririfestserver.caririfest_api.extensions.client

import com.caririfestserver.caririfest_api.model.Client
import com.caririfestserver.caririfest_api.response.ClientResponse

fun Client.toResponse(): ClientResponse {
    return ClientResponse(
        id = this.id!!,
        clientName = this.clientName,
        clientEmail = this.clientEmail,
        clientWhatsApp = this.clientWhatsApp
    )
}