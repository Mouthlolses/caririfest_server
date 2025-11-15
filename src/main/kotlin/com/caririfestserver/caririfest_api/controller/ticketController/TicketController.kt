package com.caririfestserver.caririfest_api.controller.ticketController

import com.caririfestserver.caririfest_api.request.ticket.TicketRequest
import com.caririfestserver.caririfest_api.response.TicketResponse
import com.caririfestserver.caririfest_api.service.TicketService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tickets")
class TicketController(private val ticketService: TicketService) {

    @PostMapping
    @Operation(summary = "Cria um ticket para enviar ao cliente")
    fun create(@RequestBody request: TicketRequest): TicketResponse {
        return ticketService.createTicket(request)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista um ticket referente a um id de pagamento")
    fun getTicket(@PathVariable id: Long): TicketResponse {
        return ticketService.getTicketById(id)
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Lista os tickets de um cliente")
    fun getTicketsByClient(@PathVariable clientId: Long): List<TicketResponse> {
        return ticketService.getTicketsByClient(clientId)
    }
}