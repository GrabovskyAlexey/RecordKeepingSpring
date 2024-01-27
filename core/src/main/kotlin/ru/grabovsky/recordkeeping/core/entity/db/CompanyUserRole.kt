package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 27.01.2024 17:21
 */
@Embeddable
class CompanyUserRole {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    lateinit var user: User

    @ManyToOne
    @JoinColumn(name = "company_role_id", nullable = false, updatable = false)
    lateinit var companyRole: CompanyRole
}