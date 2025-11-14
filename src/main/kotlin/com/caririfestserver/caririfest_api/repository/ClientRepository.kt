package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {}


/**
 *
 * Você vai usar para:
 *
 * Criar cliente
 *
 * Buscar cliente por email/CPF
 *
 * Validar cliente
 *
 * Listar clientes
 * */