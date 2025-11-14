package com.caririfestserver.caririfest_api.controller.clientController

import com.caririfestserver.caririfest_api.request.client.ClientRequest
import com.caririfestserver.caririfest_api.response.ClientResponse
import com.caririfestserver.caririfest_api.service.ClientService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
@RequestMapping("/clients")
class ClientController(private val clientService: ClientService) {

    @PostMapping
    fun createClient(@Valid @RequestBody request: ClientRequest): ClientResponse {
        return clientService.createClient(request)
    }

    @GetMapping("/{id}")
    fun getClientById(@PathVariable id: Long): ClientResponse {
        return clientService.getClientById(id)
    }
}