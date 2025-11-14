package com.caririfestserver.caririfest_api.controller.eventController

import com.caririfestserver.caririfest_api.extensions.event.toResponse
import com.caririfestserver.caririfest_api.request.event.EventRequest
import com.caririfestserver.caririfest_api.request.event.EventUpdateRequest
import com.caririfestserver.caririfest_api.response.EventResponse
import com.caririfestserver.caririfest_api.service.EventService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * O usuário precisa ver a lista de eventos.
 *
 * Ver detalhes do evento.
 *
 * Saber o preço do ingresso.
 *
 * Saber a disponibilidade.
 *
 *
 * CRUD Completo
 * */

@RestController
@RequestMapping("/events")
class EventController(private val eventService: EventService) {

    @PostMapping
    fun createEvent(@Valid @RequestBody request: EventRequest): EventResponse {
        return eventService.createEvent(request)
    }

    @GetMapping("/{id}")
    fun getEventById(@PathVariable id: Long): EventResponse {
        return eventService.getEventById(id)
    }

    @GetMapping
    fun getAllEvents(): List<EventResponse> {
        return eventService.getAllEvents()
    }

    @PatchMapping("/{id}")
    fun updateEvent(
        @PathVariable id: Long,
        @RequestBody request: EventUpdateRequest
    ): EventResponse {
        val update = eventService.updateEventPartial(id, request)
        return update.toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEvent(@PathVariable id: Long) {
        eventService.deleteEventById(id)
    }

}