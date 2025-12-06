package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.admin.toResponse
import com.caririfestserver.caririfest_api.model.admin.Admin
import com.caririfestserver.caririfest_api.repository.AdminRepository
import com.caririfestserver.caririfest_api.request.admin.AdminRequest
import com.caririfestserver.caririfest_api.request.admin.login.LoginRequest
import com.caririfestserver.caririfest_api.request.admin.login.LoginResponse
import com.caririfestserver.caririfest_api.response.AdminResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val repository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {


    fun createAdmin(request: AdminRequest): AdminResponse {
        val entity = Admin(
            adminName = request.adminName,
            adminLastName = request.adminLastName,
            docAdmin = request.docAdmin,
            adminEmail = request.adminEmail,
            adminEmailConfirm = request.adminEmailConfirm,
            password = passwordEncoder.encode(request.password),
        )
        val saved = repository.save(entity)
        return saved.toResponse()
    }

    fun login(request: LoginRequest): LoginResponse {
        val admin = repository.findByAdminEmail(request.email)
            ?: throw RuntimeException("Usuário não encontrado")

        val passwordCorrect = passwordEncoder.matches(request.password, admin.password)

        if (!passwordCorrect) {
            throw RuntimeException("Senha incorreta")
        }

        val token = jwtService.generateToken(admin)

        return LoginResponse(
            token = token,
            admin = admin.toResponse()
        )

    }


}