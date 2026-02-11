package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.event.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {}

/**
 * Você vai usar para:
 *
 * Cadastrar eventos
 *
 * Listar eventos
 *
 * Buscar evento por ID
 *
 * Mostrar detalhes do evento
 *
 * */