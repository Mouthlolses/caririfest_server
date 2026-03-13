package com.caririfestserver.caririfest_api.events.controller

import com.caririfestserver.caririfest_api.events.dto.EventRequest
import com.caririfestserver.caririfest_api.events.dto.EventResponse
import com.caririfestserver.caririfest_api.events.model.EventStatus
import com.caririfestserver.caririfest_api.events.service.EventService
import com.caririfestserver.caririfest_api.request.event.EventUpdateRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/events")
class EventController(
    private val eventService: EventService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createEvent(@Valid @RequestBody request: EventRequest): ResponseEntity<EventResponse> {

        val event = eventService.createEvent(request)

        return ResponseEntity
            .created(URI.create("/events/${event.id}"))
            .body(event)
    }

    @GetMapping("/{id}")
    fun getEventById(@PathVariable id: Long): EventResponse {
        return eventService.getEventById(id)
    }

    @GetMapping
    fun getAllEvents(pageable: Pageable): Page<EventResponse> {
        return eventService.getAllEvents(pageable)
    }

    @GetMapping("/status/{status}")
    fun getEventsByStatus(
        @PathVariable status: EventStatus,
        pageable: Pageable
    ): Page<EventResponse> {
        return eventService.getEventsByStatus(status, pageable)
    }

    @GetMapping("/category/{categoryId}")
    fun getEventsByCategory(
        @PathVariable categoryId: Long,
        pageable: Pageable
    ): Page<EventResponse> {
        return eventService.getEventsByCategory(categoryId, pageable)
    }

    @GetMapping("/upcoming")
    fun getUpcomingEvents(pageable: Pageable): Page<EventResponse> {
        return eventService.getUpcomingEvents(pageable)
    }

    @PatchMapping("/{id}")
    fun updateEvent(
        @PathVariable id: Long,
        @RequestBody request: EventUpdateRequest
    ): EventResponse {
        return eventService.updateEventPartial(id, request)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEvent(@PathVariable id: Long) {
        eventService.deleteEventById(id)
    }

}