package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.api.types.CompanyRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.Authority
import ru.grabovsky.recordkeeping.core.entity.db.Company
import ru.grabovsky.recordkeeping.core.entity.db.CompanyRole
import ru.grabovsky.recordkeeping.core.entity.db.User
import ru.grabovsky.recordkeeping.core.repositories.db.CompanyRoleRepository

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 17:48
 */
interface CompanyRoleService {
    fun userHasAuthority(
        user: User? = null,
        userId: Long? = null,
        company: Company? = null,
        companyId: Long? = null,
        authority: AuthorityTypes): Boolean

    fun findCompanyRoleByName(name: String): CompanyRole

    fun addUserAdminToCompany(user: User, company: Company)
}