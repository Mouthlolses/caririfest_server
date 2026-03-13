package com.caririfestserver.caririfest_api.users.service

import com.caririfestserver.caririfest_api.users.dto.CreateUserRequest
import com.caririfestserver.caririfest_api.users.model.User
import com.caririfestserver.caririfest_api.users.model.UserRole
import com.caririfestserver.caririfest_api.users.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

interface UserService {

    fun createCustomer(request: CreateUserRequest): User

    fun createOrganizer(request: CreateUserRequest): User

    fun findById(id: Long): User

    fun findByEmail(email: String): User

    fun listUsers(): List<User>

    fun deleteUser(id: Long)

}


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun createCustomer(request: CreateUserRequest): User {

        val user = User(
            name = request.name,
            lastName = request.lastName,
            doc = request.doc,
            email = request.email,
            whatsapp = request.whatsapp,
            role = UserRole.CUSTOMER
        )

        return userRepository.save(user)
    }

    override fun createOrganizer(request: CreateUserRequest): User {

        val user = User(
            name = request.name,
            lastName = request.lastName,
            doc = request.doc,
            email = request.email,
            whatsapp = request.whatsapp,
            password = passwordEncoder.encode(request.password),
            role = UserRole.ORGANIZER
        )

        return userRepository.save(user)
    }

    override fun findById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found") }
    }

    override fun findByEmail(email: String): User {
        return userRepository.findByEmail(email)
            ?: throw RuntimeException("User not found")
    }

    override fun listUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun deleteUser(id: Long) {
        return userRepository.deleteById(id)
    }
}