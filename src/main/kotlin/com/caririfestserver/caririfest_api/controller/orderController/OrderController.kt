package com.caririfestserver.caririfest_api.controller.orderController

import com.caririfestserver.caririfest_api.request.order.OrderRequest
import com.caririfestserver.caririfest_api.request.order.UpdateOrderStatusRequest
import com.caririfestserver.caririfest_api.response.OrderResponse
import com.caririfestserver.caririfest_api.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Ela vai orquestrar:
 *
 * o cliente
 *
 * os tickets comprados
 *
 * os pagamentos
 *
 * o evento
 *
 * as notificações geradas
 *
 * Por quê?
 *
 * É aqui que o usuário finaliza a compra.
 *
 * Aqui você calcula valor, valida estoque, registra os tickets e pagamentos.
 *
 * Rotas típicas:
 *
 * POST /orders
 * GET /orders/{id}
 * */
@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    @Operation(summary = "Cria um pedido(compra de ingresso)" , description = "Retorna resposta após pedido ser criado")
    fun createOrder(
        @RequestBody request: OrderRequest
    ): OrderResponse {
        return orderService.createOrder(request)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista pedido a partir de um id(lista pedido referente a um cliente)")
    fun getOrderById(@PathVariable id: Long): OrderResponse {
        return orderService.getOrderById(id)
    }

    @GetMapping
    @Operation(summary = "Lista todos os pedidos de um cliente")
    fun getAllOrders(): List<OrderResponse> {
        return orderService.getAllOrders()
    }

    /**Atualizar parcialmente um recurso(status)*/
    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualiza o status do pedido(PENDING, APPROVED, FAILED, CANCELED)")
    fun updateOrderStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateOrderStatusRequest
    ): OrderResponse {
        return orderService.updateStatusOrder(id, request)
    }
}