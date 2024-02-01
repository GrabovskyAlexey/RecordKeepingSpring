package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.api.types.CompanyRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.CompanyRole
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 31.01.2024 22:14
 */
@Repository
interface CompanyRoleRepository: JpaRepository<CompanyRole, Long> {
    fun findByName(name: String): Optional<CompanyRole>
}