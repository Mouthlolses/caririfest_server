package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.model.Client
import com.caririfestserver.caririfest_api.model.Notification
import com.caririfestserver.caririfest_api.model.Ticket
import com.caririfestserver.caririfest_api.model.canalType.CanalType
import com.caririfestserver.caririfest_api.model.shippingStatus.ShippingStatus
import com.caririfestserver.caririfest_api.repository.NotificationRepository
import org.springframework.stereotype.Service


/**
 * Envia e-mail com o ticket
 *
 * Envia WhatsApp
 *
 * Registra logs
 *
 * Reenvia em caso de falha
 *
 * Marca status como SUCCESS / FAILED
 * */
@Service
class NotificationService(
    private val repository: NotificationRepository,
    private val emailService: EmailService,          // Serviço que envia e-mails
    private val whatsappService: WhatsAppService    // Serviço que envia WhatsApp

) {

    /**
     * Cria uma notificação pendente para o cliente.
     */
    fun createNotification(
        client: Client,
        ticket: Ticket,
        canalType: CanalType,
        message: String
    ): Notification {
        val notification = Notification(
            client = client,
            ticket = ticket,
            canalType = canalType,
            textToClient = message,
            shippingStatus = ShippingStatus.PENDING
        )
        return repository.save(notification)
    }

    /**
     * Envia todas as notificações pendentes.
     * Atualiza o status para SENT ou FAILED.
     */
    fun sendPendingNotifications() {
        val pendingNotifications = repository.findByShippingStatus(ShippingStatus.PENDING)

        pendingNotifications.forEach { notification ->
            try {
//                when (notification.canalType) {
//                    CanalType.EMAIL -> emailService.send(
//                        notification.client.clientEmail,
//                        "Seu ingresso CaririFest",
//                        notification.textToClient
//                    )
//
//                    CanalType.WHATSAPP -> whatsappService.send(
//                        notification.client.clientWhatsApp.toString(),
//                        notification.textToClient
//                    )
//                }

                emailService.send(
                    notification.client.clientEmail,
                    "Seu ingresso CaririFest",
                    notification.textToClient
                )

                notification.shippingStatus = ShippingStatus.SENT
                repository.save(notification)
            } catch (ex: Exception) {
                notification.shippingStatus = ShippingStatus.FAILED
                repository.save(notification)
            }
        }
    }

    /**
     * Busca todas as notificações de um cliente específico.
     */
    fun getNotificationsByClient(clientId: Long): List<Notification> {
        return repository.findAll().filter { it.client.id == clientId }
    }

}