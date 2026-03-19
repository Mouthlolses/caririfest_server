package com.caririfestserver.caririfest_api.tickets.service

import com.caririfestserver.caririfest_api.tickets.dto.TicketResponse
import com.caririfestserver.caririfest_api.tickets.mapper.toResponse
import com.caririfestserver.caririfest_api.tickets.repository.TicketRepository
import org.springframework.stereotype.Service

//valida disponibilidade
//
//reserva tickets
//
//libera se falhar
//
//gera após pagamento

interface TicketService {

    fun getTicketsByUser(userId: Long): List<TicketResponse>

}


@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository
) : TicketService {

    override fun getTicketsByUser(userId: Long): List<TicketResponse> {
        return ticketRepository.findByUser_Id(userId)
            .map { it.toResponse() }
    }
}