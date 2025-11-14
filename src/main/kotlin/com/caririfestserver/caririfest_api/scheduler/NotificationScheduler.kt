package com.caririfestserver.caririfest_api.scheduler

import com.caririfestserver.caririfest_api.service.NotificationService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class NotificationScheduler(
    private val notificationService: NotificationService
) {
    @Scheduled(fixedDelay = 10_000) //rodando a cada 10 segundos
    fun processNotification() {
        notificationService.sendPendingNotifications()
    }
}