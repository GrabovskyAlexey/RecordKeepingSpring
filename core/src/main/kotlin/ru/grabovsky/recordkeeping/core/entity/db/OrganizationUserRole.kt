package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 27.01.2024 17:21
 */
//@Embeddable
@Entity
@Table(name = "organizations_users_roles")
class OrganizationUserRole(
    @EmbeddedId
    val role: OrganizationUserRolePK
) {
    @OneToOne
    @JoinColumn(name = "organization_role_id", nullable = false, updatable = false)
    lateinit var organizationRole: OrganizationRole
}