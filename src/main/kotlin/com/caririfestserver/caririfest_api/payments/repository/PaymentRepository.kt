package com.caririfestserver.caririfest_api.payments.repository

import com.caririfestserver.caririfest_api.payments.model.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {


}