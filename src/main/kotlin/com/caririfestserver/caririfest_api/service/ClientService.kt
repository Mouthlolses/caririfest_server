package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.client.toResponse
import com.caririfestserver.caririfest_api.model.client.Client
import com.caririfestserver.caririfest_api.repository.ClientRepository
import com.caririfestserver.caririfest_api.request.client.ClientRequest
import com.caririfestserver.caririfest_api.response.ClientResponse
import org.springframework.stereotype.Service


/**
 * Service recebe ClientRequest → cria Client → salva → retorna Client
 *
 * Validar CPF
 *
 * Validar email
 *
 * Confirmar email == emailConfirm
 *
 * Verificar se usuário já existe
 *
 * Criar cliente apenas no momento da compra
 *
 * Atualizar dados se necessário
 */

@Service
class ClientService(private val repository: ClientRepository) {

    fun createClient(request: ClientRequest): ClientResponse {
        val entity = Client(
            clientName = request.clientName,
            clientLastName = request.clientLastName,
            clientDoc = request.clientDoc,
            clientEmail = request.clientEmail,
            clientEmailConfirm = request.clientEmailConfirm,
            clientWhatsApp = request.clientWhatsApp
        )
        val saved = repository.save(entity)
        return saved.toResponse()
    }

    fun getAllClients(): List<ClientResponse> {
        return repository.findAll()
            .map { it.toResponse() }
    }

    fun getClientById(id: Long): ClientResponse {
        val entity = repository.findById(id)
            .orElseThrow { NoSuchElementException("Cliente não encontrado pelo ID $id") }

        return entity.toResponse()
    }

    fun deleteClientById(id: Long) {
        repository.deleteById(id)
    }
}