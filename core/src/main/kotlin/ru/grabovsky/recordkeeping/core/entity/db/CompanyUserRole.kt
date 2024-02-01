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
@Table(name = "companies_users_roles")
class CompanyUserRole(
    @EmbeddedId
    val role: CompanyUserRolePK
) {
    @OneToOne
    @JoinColumn(name = "company_role_id", nullable = false, updatable = false)
    lateinit var companyRole: CompanyRole
}