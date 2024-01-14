package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.core.entity.db.Role
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
interface RoleService {
    fun findByName(name: String): Optional<Role>
    fun getDefaultRole(): Role
    fun save(role: Role): Role

}