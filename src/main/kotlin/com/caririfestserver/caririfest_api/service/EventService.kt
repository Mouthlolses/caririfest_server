package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.event.toResponse
import com.caririfestserver.caririfest_api.model.event.Event
import com.caririfestserver.caririfest_api.model.event.EventStatus
import com.caririfestserver.caririfest_api.repository.EventRepository
import com.caririfestserver.caririfest_api.request.event.EventRequest
import com.caririfestserver.caririfest_api.request.event.EventUpdateRequest
import com.caririfestserver.caririfest_api.response.EventResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime


/**
 * Verificar disponibilidade de ingressos
 *
 * Calcular preço com taxa
 *
 * Ver horários e datas
 *
 * Diminuição de estoque de ingressos após compra
 *
 * Evitar sobrevenda (oversell)
 *
 * */
@Service
class EventService(private val repository: EventRepository) {

    fun createEvent(request: EventRequest): EventResponse {

        require(request.totalTickets > 0) {
            "O total de ingressos deve ser maior que zero"
        }

        val event = Event(
            title = request.title,
            description = request.description,
            bannerUrl = request.bannerUrl,
            locationName = request.locationName,
            address = request.address,
            date = request.date,
            time = request.time,
            price = request.price,
            totalTickets = request.totalTickets,
            ticketsAvailable = request.totalTickets,
            category = request.category,
            status = EventStatus.ACTIVE,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return repository.save(event).toResponse()

    }

    fun updateEventPartial(id: Long, request: EventUpdateRequest): Event {
        val event = repository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado") }

        event.apply {
            request.title?.let { title = it }
            request.description?.let { description = it }
            request.bannerUrl?.let { bannerUrl = it }
            request.address?.let { address = it }
            request.locationName?.let { locationName = it }
            request.date?.let { date = it }
            request.time?.let { time = it }
            request.price?.let { price = it }
            request.ticketsAvailable?.let { ticketsAvailable = it }
        }

        return repository.save(event)
    }

    fun getAllEvents(pageable: Pageable): Page<EventResponse> {
        return repository
            .findAll(pageable)
            .map { it.toResponse() }
    }

    fun getEventById(id: Long): EventResponse {
        val event = repository.findById(id)
            .orElseThrow { NoSuchElementException("Evento não encontrado pelo ID $id") }

        return event.toResponse()
    }

    fun deleteEventById(id: Long) {
        if (!repository.existsById(id)) {
            throw NoSuchElementException("Evento não encontrado pelo ID $id")
        } else {
            repository.deleteById(id)
        }
    }
}