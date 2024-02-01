package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.io.Serializable

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 27.01.2024 17:21
 */
@Embeddable
class CompanyUserRolePK: Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    lateinit var user: User

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    lateinit var company: Company
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CompanyUserRolePK

        if (user.id != other.user.id) return false
        if (company.id != other.company.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + company.hashCode()
        return result
    }


}