package com.caririfestserver.caririfest_api.extensions.admin

import com.caririfestserver.caririfest_api.model.admin.Admin
import com.caririfestserver.caririfest_api.response.AdminResponse


fun Admin.toResponse(): AdminResponse {
    return AdminResponse(
        id = this.id!!,
        adminName = this.adminName,
        adminLastName = this.adminLastName,
        docAdmin = this.docAdmin,
        adminEmail = this.adminEmail,
        eventId = this.event?.id
    )
}