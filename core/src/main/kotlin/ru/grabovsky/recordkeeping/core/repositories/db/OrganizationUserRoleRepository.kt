package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.db.OrganizationUserRole
import ru.grabovsky.recordkeeping.core.entity.db.OrganizationUserRolePK
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 12:13
 */
@Repository
interface OrganizationUserRoleRepository: JpaRepository<OrganizationUserRole, OrganizationUserRolePK> {
    @Query(
        """
        SELECT cr FROM OrganizationUserRole cr
        WHERE cr.role.user.id = :userId AND cr.role.organization.id = :organizationId
    """
    )
    fun findByUserIdAndCompanyId(userId: Long, organizationId: Long): Optional<OrganizationUserRole>

    @Query(
        """
        SELECT cr FROM OrganizationUserRole cr
        WHERE cr.role.user.id = :userId
    """
    )
    fun findAllByUserId(userId: Long): MutableSet<OrganizationUserRole>

    @Query(
        """
        SELECT cr FROM OrganizationUserRole cr
        WHERE cr.role.organization.id = :organizationId
    """
    )
    fun findAllByCompanyId(organizationId: Long): MutableSet<OrganizationUserRole>
}