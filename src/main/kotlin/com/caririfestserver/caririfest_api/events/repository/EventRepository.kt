package com.caririfestserver.caririfest_api.events.repository

import com.caririfestserver.caririfest_api.events.model.Event
import com.caririfestserver.caririfest_api.events.model.EventStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface EventRepository : JpaRepository<Event, Long> {

    fun findByStatus(status: EventStatus, pageable: Pageable): Page<Event>

    fun findByCategoryId(categoryId: Long, pageable: Pageable): Page<Event>

    fun findByDateAfter(date: LocalDate, pageable: Pageable): Page<Event>

}
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
 *
 * Repository deve fazer:
 *
 * ✅ filtrar
 * ✅ paginar
 * ✅ contar
 * ✅ verificar existência
 * ✅ projetar dados
 * ✅ ordenar
 * ✅ agregar
 * */