package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {}


/**
 * A Order é central no sistema (compra).
 * Você vai usar para:
 *
 * Criar pedido
 *
 * Buscar pedido por ID
 *
 * Buscar pedidos de um cliente
 *
 * Atualizar status de pagamento
 *
 * */