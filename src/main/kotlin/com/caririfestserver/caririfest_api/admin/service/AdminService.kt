package com.caririfestserver.caririfest_api.admin.service

import com.caririfestserver.caririfest_api.admin.dto.AuthResponse
import com.caririfestserver.caririfest_api.admin.dto.CreateAdmin
import com.caririfestserver.caririfest_api.admin.dto.CreateAdminResponse
import com.caririfestserver.caririfest_api.admin.mapper.toEntity
import com.caririfestserver.caririfest_api.admin.mapper.toResponse
import com.caririfestserver.caririfest_api.admin.repository.AdminRepository
import com.caririfestserver.caririfest_api.service.JwtService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

interface AdminService {

    fun createAdmin(request: CreateAdmin): CreateAdminResponse

    fun authAdmin(email: String, password: String): AuthResponse

}


@Service
class AdminServiceImpl(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) : AdminService {

    override fun createAdmin(request: CreateAdmin): CreateAdminResponse {

        if (adminRepository.existsByEmail(request.email)) {
            throw RuntimeException("Admin já existe")
        }

        val entity = request.toEntity()

        entity.password = passwordEncoder.encode(request.password)

        val saved = adminRepository.save(entity)

        return saved.toResponse()
    }


    override fun authAdmin(email: String, password: String): AuthResponse {

        val admin = adminRepository.findByEmail(email)
            ?: throw RuntimeException("Admin não encontrado")

        if (!passwordEncoder.matches(password, admin.password)) {
            throw RuntimeException("Senha inválida")
        }

        val token = jwtService.generateToken(admin)

        return AuthResponse(token)

    }


}