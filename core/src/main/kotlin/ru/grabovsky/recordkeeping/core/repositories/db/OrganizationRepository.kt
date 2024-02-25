package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.db.Organization
import ru.grabovsky.recordkeeping.core.entity.db.User

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 22:14
 */
@Repository
interface OrganizationRepository: JpaRepository<Organization, Long> {
    fun findAllByUsersContains(user: User): List<Organization>
}