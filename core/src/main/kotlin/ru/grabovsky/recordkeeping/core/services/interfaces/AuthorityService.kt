package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.core.entity.db.Authority
import ru.grabovsky.recordkeeping.core.entity.db.Role
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
interface AuthorityService {
    fun getDefaultAuthority(): Authority;
    fun findByName(name: String): Optional<Authority>
    fun save(authority: Authority): Authority
}