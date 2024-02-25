package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.core.entity.db.Organization
import ru.grabovsky.recordkeeping.core.entity.db.User
import java.security.Principal

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 17.02.2024 17:14
 */

interface AuthorizationService {
    fun userHasAuthority(
        user: User? = null,
        userId: Long? = null,
        organization: Organization? = null,
        organizationId: Long? = null,
        authority: AuthorityTypes
    ): Boolean

    fun isAdmin(principal: Principal): Boolean
    fun isActivatedUser(principal: Principal): Boolean

}