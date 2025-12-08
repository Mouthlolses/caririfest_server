package com.caririfestserver.caririfest_api.controller.adminController

import com.caririfestserver.caririfest_api.request.admin.login.LoginRequest
import com.caririfestserver.caririfest_api.request.admin.login.LoginResponse
import com.caririfestserver.caririfest_api.service.AdminService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val adminService: AdminService) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        println("JWT SECRET = ${System.getenv("JWT_SECRET")}")
        return adminService.login(request)
    }
}