package com.caririfestserver.caririfest_api.controller.adminController

import com.caririfestserver.caririfest_api.request.admin.login.LoginRequest
import com.caririfestserver.caririfest_api.request.admin.login.LoginResponse
import com.caririfestserver.caririfest_api.request.admin.recovery.ForgotPasswordRequest
import com.caririfestserver.caririfest_api.request.admin.recovery.ResetPasswordRequest
import com.caririfestserver.caririfest_api.response.MessageResponse
import com.caririfestserver.caririfest_api.service.AdminService
import com.caririfestserver.caririfest_api.service.PasswordRecoveryService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val adminService: AdminService,
    private val passwordRecoveryService: PasswordRecoveryService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        return adminService.login(request)
    }

    @PostMapping("/forgot-password")
    fun forgotPassword(
        @RequestBody request: ForgotPasswordRequest
    ): MessageResponse {
        passwordRecoveryService.requestReset(email = request.email)
        return MessageResponse("Se o email estiver cadastrado, enviaremos instruções")
    }

    @PostMapping("/reset-password")
    fun resetPassword(
        @RequestBody request: ResetPasswordRequest
    ): MessageResponse {
        passwordRecoveryService.resetPassword(token = request.token, newPassword = request.newPassword)

        return MessageResponse("Senha alterada com sucesso")
    }


}