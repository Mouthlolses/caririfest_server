package com.caririfestserver.caririfest_api.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {

    fun send(to: String, subject: String, body: String) {

        val message = SimpleMailMessage()
        message.setTo(to)
        message.subject = subject
        message.text = body
        mailSender.send(message)
    }
}