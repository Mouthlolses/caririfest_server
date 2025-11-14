package com.caririfestserver.caririfest_api.controller.orderController

import com.caririfestserver.caririfest_api.request.order.OrderRequest
import com.caririfestserver.caririfest_api.request.order.UpdateOrderStatusRequest
import com.caririfestserver.caririfest_api.response.OrderResponse
import com.caririfestserver.caririfest_api.service.OrderService
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
    fun createOrder(
        @RequestBody request: OrderRequest
    ): OrderResponse {
        return orderService.createOrder(request)
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): OrderResponse {
        return orderService.getOrderById(id)
    }

    @GetMapping
    fun getAllOrders(): List<OrderResponse> {
        return orderService.getAllOrders()
    }

    /**Atualizar parcialmente um recurso(status)*/
    @PatchMapping("/{id}/status")
    fun updateOrderStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateOrderStatusRequest
    ): OrderResponse {
        return orderService.updateStatusOrder(id, request)
    }
}