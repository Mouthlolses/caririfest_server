package com.caririfestserver.caririfest_api.model

import com.caririfestserver.caririfest_api.model.useStatus.UseStatus
import jakarta.persistence.*
import java.time.LocalDateTime

/**A representação do bilhete, gerada após a confirmação do pagamento*/
@Entity
@Table(name = "tickets")
data class Ticket(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**Liga ao Pedido que originou este ingresso.*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    /**Liga ao Evento ao qual pertence.*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    val event: Event,

    /**O cliente que é o dono do ingresso.*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    val client: Client,

    /**Código único (QR Code ou código de barras) para validação.*/
    /**Column garantindo que ele seja unico no banco de dados e não nulo.*/
    @Column(unique = true, nullable = false)
    val accessCode: String,

    /**Crucial: Valores como NAO_USADO, USADO, CANCELADO.*/
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    val useStatus: UseStatus,

    /**Crucial: Data e Hora que foi utilizado,o uso pode ser nulo inicialmente*/
    val dataUso: LocalDateTime? = null

)
