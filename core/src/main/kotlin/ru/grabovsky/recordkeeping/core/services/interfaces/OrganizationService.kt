package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationInfoResponseDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationShortInfoDto
import java.security.Principal

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 21.01.2024 15:28
 */
interface OrganizationService {
    fun getAllOrganization(principal: Principal): OrganizationInfoResponseDto
    fun getOrganizationById(id: Long, principal: Principal): OrganizationDto
    fun createOrganization(organizationDto: OrganizationShortInfoDto, principal: Principal): OrganizationShortInfoDto
    fun updateOrganization(id: Long, organizationDto: OrganizationShortInfoDto, principal: Principal)
    fun deleteOrganization(id: Long, principal: Principal)
}