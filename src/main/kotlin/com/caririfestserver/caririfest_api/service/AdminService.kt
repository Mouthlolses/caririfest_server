package com.caririfestserver.caririfest_api.service

import com.caririfestserver.caririfest_api.extensions.admin.toResponse
import com.caririfestserver.caririfest_api.model.admin.Admin
import com.caririfestserver.caririfest_api.repository.AdminRepository
import com.caririfestserver.caririfest_api.request.admin.AdminRequest
import com.caririfestserver.caririfest_api.response.AdminResponse
import org.springframework.stereotype.Service

@Service
class AdminService(private val repository: AdminRepository) {


    fun createAdmin(request: AdminRequest) : AdminResponse {
        val entity = Admin(
            adminName = request.adminName,
            adminLastName = request.adminLastName,
            docAdmin = request.docAdmin,
            adminEmail = request.adminEmail,
            adminEmailConfirm = request.adminEmailConfirm,
            password = request.password,
        )
        val saved = repository.save(entity)
        return saved.toResponse()
    }




}