package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {}

/**
 * Você vai usar para:
 *
 * Registrar pagamento vindo de um gateway
 *
 * Buscar pagamentos de um pedido
 *
 * Verificar status
 *
 * Atualizar info do payment
 *
 * */