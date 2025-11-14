package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.event.toResponse
import com.caririfestserver.caririfest_api.model.Event
import com.caririfestserver.caririfest_api.repository.EventRepository
import com.caririfestserver.caririfest_api.request.event.EventRequest
import com.caririfestserver.caririfest_api.request.event.EventUpdateRequest
import com.caririfestserver.caririfest_api.response.EventResponse
import org.springframework.stereotype.Service


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
        val event = Event(
            title = request.title,
            local = request.local,
            date = request.date,
            time = request.time,
            price = request.price,
            ticketsAvailable = request.ticketsAvailable,
        )
        if (request.ticketsAvailable < 0) {
            throw IllegalArgumentException("Quantidade de ingressos não pode ser negativa")
        } else {
            val event = repository.save(event)
            return event.toResponse()
        }
    }

    fun updateEventPartial(id: Long, request: EventUpdateRequest): Event {
        val event = repository.findById(id)
            .orElseThrow { NoSuchElementException("Evento não encontrado pelo ID $id") }

        request.title?.let { event.title = it }
        request.local?.let { event.local = it }
        request.date?.let { event.date = it }
        request.time?.let { event.time = it }
        request.price?.let { event.price = it }
        request.ticketsAvailable?.let { event.ticketsAvailable = it }

        return repository.save(event)
    }

    fun getAllEvents(): List<EventResponse> {
        return repository.findAll()
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