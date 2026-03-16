package com.caririfestserver.caririfest_api.customers.controller

import com.caririfestserver.caririfest_api.tickets.dto.TicketResponse
import com.caririfestserver.caririfest_api.tickets.service.TicketService
import com.caririfestserver.caririfest_api.customers.dto.CreateUserRequest
import com.caririfestserver.caririfest_api.customers.dto.UserResponse
import com.caririfestserver.caririfest_api.customers.mapper.toResponse
import com.caririfestserver.caririfest_api.customers.service.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

/**
 * O usuário vai preencher os dados pessoais antes da compra.
 *
 * E isso será enviado ao backend.
 *
 * Rotas típicas:
 *
 * POST /clients (salva dados do cliente)
 * */
@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val ticketService: TicketService
) {

    @PostMapping("/customers")
    @Operation(summary = "Criar cliente")
    fun createCustomer(@Valid @RequestBody request: CreateUserRequest): UserResponse {
        return userService.createCustomer(request).toResponse()
    }

    @PostMapping("/organizers")
    @Operation(summary = "Criar organizador")
    fun createOrganizer(@Valid @RequestBody request: CreateUserRequest): UserResponse {
        return userService.createOrganizer(request).toResponse()
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    fun getAllUsers(): List<UserResponse> {
        return userService.listUsers().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    fun getUserById(@PathVariable id: Long): UserResponse {
        return userService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

    //tickets for user
    @GetMapping("/{id}/tickets")
    fun getUserTickets(@PathVariable id: Long): List<TicketResponse> {
        return ticketService.getTicketsByUser(id)
    }
}