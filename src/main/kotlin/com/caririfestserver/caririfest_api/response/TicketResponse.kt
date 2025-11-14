package com.caririfestserver.caririfest_api.response

import com.caririfestserver.caririfest_api.model.useStatus.UseStatus
import java.time.LocalDateTime

data class TicketResponse(
    val id: Long,
    val eventId: Long,
    val clientId: Long,
    val orderId: Long,
    val accessCode: String,
    val useStatus: UseStatus,
    val dataUso: LocalDateTime?
)