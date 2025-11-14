package com.caririfestserver.caririfest_api.model


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime


/**Entidade que representa o produto ou serviço que está sendo vendido (nesse caso o evento em si)*/
@Entity
@Table(name = "events")
data class Event(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    /**Título do Evento*/
    var title: String,

    /**Local do Evento*/
    var local: String,

    /**Data do Evento*/
    var date: LocalDate,

    /**Hora do Evento*/
    var time: LocalTime,

    /**Valor do ingresso*/
    /**Column com precision para limitar casas decimais*/
    @Column(precision = 10, scale = 2)
    var price: BigDecimal,

    /**Quantidade de ingressos disponiveis*/
    var ticketsAvailable: Int,


    /**
     * Esses campos não criam novas tabelas — apenas permitem que, no código, você acesse facilmente tudo que está ligado a um evento.
     *
     * Exemplo prático:
     *
     * val event = eventRepository.findById(1)
     * println(event.tickets.size)  // mostra quantos ingressos foram gerados pra esse evento
     *
     * */
    /**Pedidos feitos para este evento*/
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: List<Order> = emptyList(),

    /**Ingressos associados a este evento*/
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tickets: List<Ticket> = emptyList()
)
