package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.ticket.toResponse
import com.caririfestserver.caririfest_api.model.Ticket
import com.caririfestserver.caririfest_api.model.canalType.CanalType
import com.caririfestserver.caririfest_api.model.useStatus.UseStatus
import com.caririfestserver.caririfest_api.repository.ClientRepository
import com.caririfestserver.caririfest_api.repository.EventRepository
import com.caririfestserver.caririfest_api.repository.OrderRepository
import com.caririfestserver.caririfest_api.repository.TicketRepository
import com.caririfestserver.caririfest_api.request.ticket.TicketRequest
import com.caririfestserver.caririfest_api.response.TicketResponse
import org.springframework.stereotype.Service
import java.util.*


/**
 * Gerar tickets após pagamento aprovado
 *
 * Gerar QR Code / Código de acesso
 *
 * Validar ticket na portaria
 *
 * Marcar como usado
 *
 * Evitar fraude
 * */
@Service
class TicketService(
    private val repository: TicketRepository,
    private val eventRepository: EventRepository,
    private val clientRepository: ClientRepository,
    private val orderRepository: OrderRepository,
    private val notificationService: NotificationService

) {

    fun createTicket(request: TicketRequest): TicketResponse {
        val order = orderRepository.findById(request.orderId)
            .orElseThrow { NoSuchElementException("Pedido não encontrado: ${request.orderId}") }

        val event = eventRepository.findById(request.eventId)
            .orElseThrow { NoSuchElementException("Evento não encontrado: ${request.eventId}") }

        val client = clientRepository.findById(request.clientId)
            .orElseThrow { NoSuchElementException("Cliente não encontrado: ${request.clientId}") }

        // Valida disponibilidade de ingressos
        if (event.ticketsAvailable <= 0) {
            throw IllegalStateException("Não há ingressos disponíveis para o evento ${event.title}")
        }

        event.ticketsAvailable -= 1
        eventRepository.save(event)

        // Gera código único para o ingresso
        val accessCode = UUID.randomUUID().toString()  // código único do ingresso

        // Cria ticket
        val ticket = Ticket(
            order = order,
            event = event,
            client = client,
            accessCode = accessCode,
            useStatus = UseStatus.NO_USE
        )

        val saved = repository.save(ticket)

        // Cria notificações para o cliente
        notificationService.createNotification(
            client = client,
            ticket = saved,
            canalType = CanalType.EMAIL,
            message = "Seu ingresso para o evento ${event.title} foi gerado! Código: ${saved.accessCode}"
        )

        return saved.toResponse()
    }

    fun getTicketById(id: Long): TicketResponse {
        val ticket = repository.findById(id)
            .orElseThrow { NoSuchElementException("Ticket não encontrado id=$id") }
        return ticket.toResponse()
    }

    fun getTicketsByClient(clientId: Long): List<TicketResponse> {
        val tickets = repository.findAllByClientId(clientId)
        if (tickets.isEmpty()) throw NoSuchElementException("Tickets do client não encontrados ids=$clientId")
        return tickets.toResponse()
    }

}