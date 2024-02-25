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
class OrganizationUserRolePK: Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    lateinit var user: User

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false, updatable = false)
    lateinit var organization: Organization
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrganizationUserRolePK

        if (user.id != other.user.id) return false
        if (organization.id != other.organization.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + organization.hashCode()
        return result
    }


}