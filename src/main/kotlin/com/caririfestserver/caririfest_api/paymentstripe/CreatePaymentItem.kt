package com.caririfestserver.caririfest_api.paymentstripe

import com.google.gson.annotations.SerializedName

data class CreatePaymentItem(
    @SerializedName("id") val id: String,
    @SerializedName("amount") val amount: Long
)

data class CreatePayment(
    @SerializedName("items") val items: Array<CreatePaymentItem>
)

data class CreatePaymentResponse(
    val clientSecret: String
)