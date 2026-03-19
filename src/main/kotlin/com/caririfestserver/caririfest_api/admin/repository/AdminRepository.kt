package com.caririfestserver.caririfest_api.admin.repository

import com.caririfestserver.caririfest_api.admin.model.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<Admin, Long> {

    fun findByEmail(email: String): Admin?

    fun existsByEmail(email: String): Boolean
}