package com.caririfestserver.caririfest_api.model

import com.caririfestserver.caririfest_api.model.canalType.CanalType
import com.caririfestserver.caririfest_api.model.shippingStatus.ShippingStatus
import jakarta.persistence.*
import java.time.LocalDateTime


/**Rastreia e gerencia o envio de emails e mensagens de WhatsApp*/
@Entity
@Table(name = "notifications")
data class Notification(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**Cliente que deve receber a notificação..*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    val client: Client,

    /**Opcional: Liga à entidade do ingresso enviado.*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    val ticket: Ticket? = null,

    /**EMAIL, WHATSAPP..*/
    @Enumerated(value = EnumType.STRING)
    val canalType: CanalType,

    /**O conteúdo da mensagem (assunto do email ou texto do WhatsApp). Column para limitar caracteres na mensagem*/
    @Column(length = 1000)
    val textToClient: String,

    /** Status do envio da mensagem. Column para que nunca seja um campo vazio*/
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    var shippingStatus: ShippingStatus = ShippingStatus.PENDING,

    /**Momento em que a notificação foi enviada (ou tentativa feita).*/
    val sentAt: LocalDateTime? = null

)
