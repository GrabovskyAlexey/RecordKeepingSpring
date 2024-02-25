package ru.grabovsky.recordkeeping.core.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationShortInfoDto
import ru.grabovsky.recordkeeping.core.annotations.secutiry.AllowActivatedUser
import ru.grabovsky.recordkeeping.core.controllers.interfaces.OrganizationController
import ru.grabovsky.recordkeeping.core.services.interfaces.OrganizationService
import java.security.Principal

@RestController
@RequestMapping("\${application.api.url}/organization")
class OrganizationControllerImpl(
    private val organizationService: OrganizationService
) : OrganizationController {
    @AllowActivatedUser
    override fun getAllOrganization(principal: Principal) = organizationService.getAllOrganization(principal)
    @AllowActivatedUser
    override fun getOrganizationById(id: Long, principal: Principal) = organizationService.getOrganizationById(id, principal)
    @AllowActivatedUser
    override fun createOrganization(organization: OrganizationShortInfoDto, principal: Principal) = organizationService.createOrganization(organization, principal)
    @AllowActivatedUser
    override fun updateOrganization(id: Long, organization: OrganizationShortInfoDto, principal: Principal) = organizationService.updateOrganization(id, organization, principal)
    @AllowActivatedUser
    override fun deleteOrganization(id: Long, principal: Principal) = organizationService.deleteOrganization(id, principal)
}