package com.caririfestserver.caririfest_api.repository

import com.caririfestserver.caririfest_api.model.admin.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<Admin, Long> {

    fun findByAdminEmail(adminEmail: String): Admin?
}