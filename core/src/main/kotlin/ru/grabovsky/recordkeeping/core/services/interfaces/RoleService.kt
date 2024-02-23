package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.types.ApplicationRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.Role
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
interface RoleService {
    fun findByName(name: ApplicationRoleTypes): Optional<Role>
    fun getDefaultRole(): Role
    fun save(role: Role): Role

}