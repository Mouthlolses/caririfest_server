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

    fun sendPasswordResetEmail(to: String, link: String){
        val subject = "Redefinição de senha"

        val body = """
            Olá,

            Recebemos uma solicitação para redefinir sua senha.

            Para criar uma nova senha, clique no link abaixo:
            $link

            Este link é válido por 30 minutos.

            Se você não solicitou a redefinição, ignore este email.

            Atenciosamente,
            Equipe Cariri Fest
        """.trimIndent()

        send(to, subject, body)
    }
}