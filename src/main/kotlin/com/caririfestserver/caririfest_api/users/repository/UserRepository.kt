package com.caririfestserver.caririfest_api.users.repository

import com.caririfestserver.caririfest_api.users.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

}