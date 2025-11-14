package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.Notification
import com.caririfestserver.caririfest_api.model.shippingStatus.ShippingStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByShippingStatus(status: ShippingStatus): List<Notification>
}

/**
 * Você vai usar:
 *
 * Registrar envio de emails/whatsapp
 *
 * Verificar histórico de notificações
 *
 * Reenviar mensagens não enviadas
 *
 * Marcar como "SUCCESS" / "FAILED"
 *
 * Se você vai armazenar logs de envio, crie repository.
 *
 *
 *
 * */