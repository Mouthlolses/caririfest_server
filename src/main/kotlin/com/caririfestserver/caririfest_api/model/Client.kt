package com.caririfestserver.caririfest_api.model

import jakarta.persistence.*


/**Entidade que armazena os dados pessoais do comprador, necessária para comunicação e envio dos ingressos*/
@Entity
@Table(name = "clients")
data class Client(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    /**Nome do cliente*/
    val clientName: String,

    /**Sobrenome do cliente*/
    val clientLastName: String,

    /**Documento do cliente(CPF)*/
    val clientDoc: String,

    /**Email do cliente para recebimento dos ingressos*/
    val clientEmail: String,

    /**Confirmação do email do cliente, é necessario para de fato receber os ingressos*/
    val clientEmailConfirm: String,

    /**Numero do whatsapp - para recebimento do ingresso, pode ser uma das opções caso cliente preferir*/
    val clientWhatsApp: String,


    /**Pedidos feitos por este cliente*/
    @OneToMany(mappedBy = "clients", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: List<Order> = emptyList(),

    /**Ingressos associados a este cliente*/
    @OneToMany(mappedBy = "clients", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tickets: List<Ticket> = emptyList(),

    /**Notificações enviadas a este cliente*/
    @OneToMany(mappedBy = "clients", cascade = [CascadeType.ALL], orphanRemoval = true)
    val notifications: List<Notification> = emptyList()
)
