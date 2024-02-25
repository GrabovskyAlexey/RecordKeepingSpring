package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.core.entity.db.Organization
import ru.grabovsky.recordkeeping.core.entity.db.OrganizationRole
import ru.grabovsky.recordkeeping.core.entity.db.OrganizationUserRole
import ru.grabovsky.recordkeeping.core.entity.db.User

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 17:48
 */
interface OrganizationRoleService {
    fun findOrganizationRoleByName(name: String): OrganizationRole
    fun addUserAdminToOrganization(user: User, organization: Organization)
    fun getUserOrganizationAuthority(userId: Long, organizationId: Long): OrganizationUserRole
}