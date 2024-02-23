package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.core.entity.db.Company
import ru.grabovsky.recordkeeping.core.entity.db.CompanyRole
import ru.grabovsky.recordkeeping.core.entity.db.CompanyUserRole
import ru.grabovsky.recordkeeping.core.entity.db.User

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 17:48
 */
interface CompanyRoleService {
    fun findCompanyRoleByName(name: String): CompanyRole
    fun addUserAdminToCompany(user: User, company: Company)
    fun getUserCompanyAuthority(userId: Long, companyId: Long): CompanyUserRole
}