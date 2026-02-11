package com.caririfestserver.caririfest_api.controller.eventController

import com.caririfestserver.caririfest_api.extensions.event.toResponse
import com.caririfestserver.caririfest_api.request.event.EventRequest
import com.caririfestserver.caririfest_api.request.event.EventUpdateRequest
import com.caririfestserver.caririfest_api.response.EventResponse
import com.caririfestserver.caririfest_api.service.EventService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

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
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar evento", description = "Cria evento e retorna os dados dele")
    fun createEvent(@Valid @RequestBody request: EventRequest): ResponseEntity<EventResponse?>? {

        val event = eventService.createEvent(request)

        return ResponseEntity
            .created(URI.create("/events/${event.id}"))
            .body(event)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista evento por id", description = "Retorna os dados de um evento")
    fun getEventById(@PathVariable id: Long): EventResponse {
        return eventService.getEventById(id)
    }

    @GetMapping
    @Operation(summary = "Lista todos os eventos", description = "Retorna uma lista com todos os eventos")
    fun getAllEvents(
        pageable: Pageable
    ): Page<EventResponse> {
        return eventService.getAllEvents(pageable)
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente ou totalmente os dados de um evento", description = "Retorna os dados do evento que foram atualizados")
    fun updateEvent(
        @PathVariable id: Long,
        @RequestBody request: EventUpdateRequest
    ): EventResponse {
        val update = eventService.updateEventPartial(id, request)
        return update.toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um evento por id", description = "Deleta evento")
    fun deleteEvent(@PathVariable id: Long) {
        eventService.deleteEventById(id)
    }

}