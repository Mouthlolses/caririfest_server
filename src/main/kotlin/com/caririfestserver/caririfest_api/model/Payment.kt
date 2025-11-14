package com.caririfestserver.caririfest_api.model

import com.caririfestserver.caririfest_api.model.paymentMethod.PaymentMethod
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

/**Armazena os detalhes da interação com o Provedor de Pagamento (Gateway)*/
@Entity
@Table(name = "payments")
data class Payment(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**Liga ao Pedido que este pagamento se refere*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    /**ID retornado pelo sistema de pagamento*/
    val gatewayTransactionId: String,

    /**Métodos de pagamento oferecidos pela API*/
    @Enumerated(value = EnumType.STRING)
    val paymentMethod: PaymentMethod,

    /**Data e hora em que o gateway confirmou o sucesso.*/
    val dateConfirmation: LocalDateTime = LocalDateTime.now(),

    /**Número total de parcelas do pedido (se aplicável)*/
    val totalInstallments: Int,

    /**Número desta parcela (ex: 1, 2, 3...)*/
    val installments: Int,

    /**Valor efetivamente pago (incluindo taxas, se houver).*/
    @Column(precision = 10, scale = 2)
    val amountPaid: BigDecimal
)
