package ru.grabovsky.recordkeeping.core.services

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.types.ApplicationRoleTypes
import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.core.entity.db.Company
import ru.grabovsky.recordkeeping.core.entity.db.User
import ru.grabovsky.recordkeeping.core.services.interfaces.AuthorizationService
import ru.grabovsky.recordkeeping.core.services.interfaces.CompanyRoleService
import java.security.Principal

@Service
class AuthorizationServiceImpl(
    private val companyRoleService: CompanyRoleService
) : AuthorizationService {
    override fun userHasAuthority(
        user: User?,
        userId: Long?,
        company: Company?,
        companyId: Long?,
        authority: AuthorityTypes
    ): Boolean {
        val role = companyRoleService.getUserCompanyAuthority(
            user?.id ?: userId ?: 0,
            company?.id ?: companyId ?: 0
        )
        return role.companyRole.authorities.any { it.type == authority }
    }

    override fun isAdmin(principal: Principal): Boolean {
        if(principal is Authentication) {
            return principal.authorities.any { it.authority.equals(ApplicationRoleTypes.ROLE_APP_ADMIN.toString()) }
        }
        return false;
    }

    override fun isActivatedUser(principal: Principal): Boolean {
        if(principal is Authentication) {
            return principal.authorities.any {
                it.authority.equals(ApplicationRoleTypes.ROLE_APP_ADMIN.toString())
                        || it.authority.equals(ApplicationRoleTypes.ROLE_ACTIVATED_USER.toString())
            }
        }
        return false;
    }
}