package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.types.OrganizationRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.*
import ru.grabovsky.recordkeeping.core.exceptions.ForbiddenOperationException
import ru.grabovsky.recordkeeping.core.exceptions.NotFoundException
import ru.grabovsky.recordkeeping.core.repositories.db.OrganizationRoleRepository
import ru.grabovsky.recordkeeping.core.repositories.db.OrganizationUserRoleRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.OrganizationRoleService
import ru.grabovsky.recordkeeping.core.services.interfaces.LocalizationService

@Service
class OrganizationRoleServiceImpl(
    private val organizationUserRoleRepository: OrganizationUserRoleRepository,
    private val organizationRoleRepository: OrganizationRoleRepository,
    private val localizationService: LocalizationService
) : OrganizationRoleService {

    override fun getUserOrganizationAuthority(userId: Long, organizationId: Long): OrganizationUserRole {
        return organizationUserRoleRepository.findByUserIdAndCompanyId(userId, organizationId)
            .orElseThrow { ForbiddenOperationException(localizationService.getErrorMessage("role.organization.forbidden")) };
    }

    override fun findOrganizationRoleByName(name: String): OrganizationRole {
        return organizationRoleRepository.findByName(name).orElseThrow { NotFoundException(localizationService.getErrorMessage("role.notFound")) }
    }

    override fun addUserAdminToOrganization(user: User, organization: Organization) {
        val pk = OrganizationUserRolePK()
        pk.organization = organization;
        pk.user = user
        val organizationUserRole = OrganizationUserRole(pk)
        organizationUserRole.organizationRole = findOrganizationRoleByName(OrganizationRoleTypes.ROLE_ORGANIZATION_ADMIN.toString());
        organizationUserRoleRepository.save(organizationUserRole);
    }
}