package com.caririfestserver.caririfest_api.tickets.repository

import com.caririfestserver.caririfest_api.tickets.dto.TicketResponse
import com.caririfestserver.caririfest_api.tickets.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TicketRepository : JpaRepository<Ticket, Long> {

    @Query("""
    SELECT t FROM Ticket t
    JOIN FETCH t.event
    WHERE t.user.id = :userId
""")
    fun findByUser_Id(userId: Long): List<Ticket>

    fun findByAccessCode(accessCode: UUID): Ticket?

}
