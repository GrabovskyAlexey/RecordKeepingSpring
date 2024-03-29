package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.core.entity.db.Authority
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
interface AuthorityService {
    fun getDefaultAuthority(): Authority
    fun findByType(type: AuthorityTypes): Optional<Authority>
    fun save(authority: Authority): Authority
}