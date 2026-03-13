package com.caririfestserver.caririfest_api.events.service

import com.caririfestserver.caririfest_api.events.dto.EventRequest
import com.caririfestserver.caririfest_api.events.dto.EventResponse
import com.caririfestserver.caririfest_api.events.mapper.toResponse
import com.caririfestserver.caririfest_api.events.model.Event
import com.caririfestserver.caririfest_api.events.model.EventStatus
import com.caririfestserver.caririfest_api.events.repository.EventRepository
import com.caririfestserver.caririfest_api.request.event.EventUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.time.LocalDate

interface EventService {

    fun createEvent(request: EventRequest): EventResponse

    fun getEventById(id: Long): EventResponse

    fun getAllEvents(pageable: Pageable): Page<EventResponse>

    fun getEventsByStatus(status: EventStatus, pageable: Pageable): Page<EventResponse>

    fun getEventsByCategory(categoryId: Long, pageable: Pageable): Page<EventResponse>

    fun getUpcomingEvents(pageable: Pageable): Page<EventResponse>

    fun updateEventPartial(id: Long, request: EventUpdateRequest): EventResponse

    fun deleteEventById(id: Long)

}


@Service
class EventServiceImpl(
    private val repository: EventRepository
) : EventService {

    override fun createEvent(request: EventRequest): EventResponse {

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
            status = EventStatus.ACTIVE
        )

        return repository.save(event).toResponse()
    }

    override fun getEventById(id: Long): EventResponse {
        val event = repository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado") }

        return event.toResponse()
    }

    override fun getAllEvents(pageable: Pageable): Page<EventResponse> {
        return repository
            .findAll(pageable)
            .map { it.toResponse() }
    }

    override fun getEventsByStatus(status: EventStatus, pageable: Pageable): Page<EventResponse> {
        return repository
            .findByStatus(status, pageable)
            .map { it.toResponse() }
    }

    override fun getEventsByCategory(categoryId: Long, pageable: Pageable): Page<EventResponse> {
        return repository
            .findByCategoryId(categoryId, pageable)
            .map { it.toResponse() }
    }

    override fun getUpcomingEvents(pageable: Pageable): Page<EventResponse> {
        return repository
            .findByDateAfter(LocalDate.now(), pageable)
            .map { it.toResponse() }
    }

    override fun updateEventPartial(id: Long, request: EventUpdateRequest): EventResponse {

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
            request.totalTickets?.let { totalTickets = it }

            updatedAt = Instant.now()
        }

        return repository.save(event).toResponse()
    }

    override fun deleteEventById(id: Long) {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado")
        }

        repository.deleteById(id)
    }

}