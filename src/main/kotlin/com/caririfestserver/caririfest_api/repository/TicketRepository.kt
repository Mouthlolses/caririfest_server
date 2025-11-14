package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : JpaRepository<Ticket, Long> {
    fun findAllByClientId(clientId: Long): List<Ticket>
}


/**
 *
 * Você vai usar para:
 *
 * Criar ticket após pagamento
 *
 * Validar ticket (QR Code / accessCode)
 *
 * Buscar todos os tickets do cliente
 *
 * Marcar como usado
 *
 * */