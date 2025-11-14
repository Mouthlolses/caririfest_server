package com.caririfestserver.caririfest_api.request.payment

import com.caririfestserver.caririfest_api.model.paymentMethod.PaymentMethod
import java.math.BigDecimal

data class PaymentRequest(
    val orderId: Long,
    val paymentMethod: PaymentMethod,
    val totalInstallments: Int = 1,    // default 1 (à vista)
    val amountPaid: BigDecimal

)