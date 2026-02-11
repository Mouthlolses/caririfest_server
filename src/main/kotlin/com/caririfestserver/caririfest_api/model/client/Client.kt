package com.caririfestserver.caririfest_api.model.client

import com.caririfestserver.caririfest_api.annotations.NotEmpty
import com.caririfestserver.caririfest_api.model.Notification
import com.caririfestserver.caririfest_api.model.Order
import com.caririfestserver.caririfest_api.model.Ticket
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

/**Entidade que armazena os dados pessoais do comprador, necessária para comunicação e envio dos ingressos*/
@Entity
@Table(name = "clients")
data class Client(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    /**Nome do cliente*/
    @field:NotEmpty val clientName: String,

    /**Sobrenome do cliente*/
    @field:NotEmpty val clientLastName: String,

    /**Documento do cliente(CPF)*/
    @field:NotEmpty val clientDoc: String,

    /**Email do cliente para recebimento dos ingressos*/
    @field:NotEmpty val clientEmail: String,

    /**Confirmação do email do cliente, é necessario para de fato receber os ingressos*/
    @field:NotEmpty val clientEmailConfirm: String,

    /**Numero do whatsapp - para recebimento do ingresso, pode ser uma das opções caso cliente preferir*/
    val clientWhatsApp: String,


    /**Pedidos feitos por este cliente*/
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: List<Order> = emptyList(),

    /**Ingressos associados a este cliente*/
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tickets: List<Ticket> = emptyList(),

    /**Notificações enviadas a este cliente*/
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    val notifications: List<Notification> = emptyList()
)