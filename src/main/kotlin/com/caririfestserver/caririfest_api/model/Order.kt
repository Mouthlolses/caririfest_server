package com.caririfestserver.caririfest_api.model

import com.caririfestserver.caririfest_api.model.statusPayment.StatusPayment
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

/**Entidade para o pedido(ordem/transação) do cliente*/
@Entity
@Table(name = "orders")
data class Order(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**@ManyToOne indica que muitos pedidos podem ter o mesmo cliente*/
    /**fetch = FetchType.LAZY faz o carregamento sob demanda (melhor performance)*/
    /**@JoinColumn(name = "client_id") define o nome da coluna FK no banco*/
    /**Liga ao Cliente que fez o pedido*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    val client: Client,

    /**Evento relacionado ao pedido.*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    val event: Event,

    /**Momento em que o pedido foi criado*/
    val dateHourOrder: LocalDateTime,

    /**Valor total pago pelo pedido(incluindo taxas)*/
    val totalPrice: BigDecimal,

    /**Status do pagamento(PENDING,APPROVED,FAILED,CANCELED)*/
    @Enumerated(value = EnumType.STRING)
    var statusPayment: StatusPayment,

    /**Pagamentos associados a este pedido.*/
    @OneToMany(mappedBy = "orders", cascade = [CascadeType.ALL], orphanRemoval = true)
    val payments: List<Payment> = emptyList(),

    /**
     * Assim, quando um pedido é confirmado e os ingressos são gerados,
    você pode simplesmente fazer:
    order.tickets.addAll(ingressosGerados)
    orderRepository.save(order)
    E o cascade cuida de salvar todos os Tickets junto.
    (sem precisar chamar ticketRepository.save() um a um)
     */
    @OneToMany(mappedBy = "orders", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tickets: MutableList<Ticket> = mutableListOf()
)
