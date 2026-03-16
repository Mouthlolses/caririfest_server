package com.caririfestserver.caririfest_api.customers.repository

import com.caririfestserver.caririfest_api.customers.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

}