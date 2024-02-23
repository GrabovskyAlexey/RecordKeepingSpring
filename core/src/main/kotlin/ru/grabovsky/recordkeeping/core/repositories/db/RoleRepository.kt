package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.api.types.ApplicationRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.Role
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
@Repository
interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: ApplicationRoleTypes): Optional<Role>
}