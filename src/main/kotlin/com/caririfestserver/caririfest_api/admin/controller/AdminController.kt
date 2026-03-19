package com.caririfestserver.caririfest_api.admin.controller

import com.caririfestserver.caririfest_api.admin.dto.AuthRequest
import com.caririfestserver.caririfest_api.admin.dto.AuthResponse
import com.caririfestserver.caririfest_api.admin.dto.CreateAdmin
import com.caririfestserver.caririfest_api.admin.dto.CreateAdminResponse
import com.caririfestserver.caririfest_api.admin.service.AdminService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminService: AdminService
) {

    @PostMapping
    @Operation(summary = "Criar admin")
    fun createAdmin(@RequestBody request: CreateAdmin): CreateAdminResponse {
        return adminService.createAdmin(request)
    }

    @PostMapping("/auth")
    fun authAdmin(@RequestBody request: AuthRequest): AuthResponse {
        return adminService.authAdmin(request.email, request.password)
    }

}