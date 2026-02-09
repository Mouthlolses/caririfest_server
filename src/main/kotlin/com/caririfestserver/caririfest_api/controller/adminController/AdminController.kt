package com.caririfestserver.caririfest_api.controller.adminController

import com.caririfestserver.caririfest_api.request.admin.AdminRequest
import com.caririfestserver.caririfest_api.response.AdminResponse
import com.caririfestserver.caririfest_api.service.AdminService
import io.micrometer.observation.annotation.Observed
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admins")
class AdminController(private val adminService: AdminService) {

    @PostMapping
    fun createAdmin(@Valid @RequestBody request: AdminRequest) : AdminResponse {
        return adminService.createAdmin(request)
    }

}