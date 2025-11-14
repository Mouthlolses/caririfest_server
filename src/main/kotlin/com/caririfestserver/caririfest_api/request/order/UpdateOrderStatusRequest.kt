package com.caririfestserver.caririfest_api.request.order

import com.caririfestserver.caririfest_api.model.statusPayment.StatusPayment

data class UpdateOrderStatusRequest(
    val statusPayment: StatusPayment
)