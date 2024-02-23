package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.types.CompanyRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.*
import ru.grabovsky.recordkeeping.core.exceptions.ForbiddenOperationException
import ru.grabovsky.recordkeeping.core.exceptions.NotFoundException
import ru.grabovsky.recordkeeping.core.repositories.db.CompanyRoleRepository
import ru.grabovsky.recordkeeping.core.repositories.db.CompanyUserRoleRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.CompanyRoleService

@Service
class CompanyRoleServiceImpl(
    private val companyUserRoleRepository: CompanyUserRoleRepository,
    private val companyRoleRepository: CompanyRoleRepository
) : CompanyRoleService {

    override fun getUserCompanyAuthority(userId: Long, companyId: Long): CompanyUserRole {
        return companyUserRoleRepository.findByUserIdAndCompanyId(userId, companyId)
            .orElseThrow { ForbiddenOperationException("У пользователя нет соответствующих прав в организации") };
    }

    override fun findCompanyRoleByName(name: String): CompanyRole {
        return companyRoleRepository.findByName(name).orElseThrow { NotFoundException("Роль не найдена") }
    }

    override fun addUserAdminToCompany(user: User, company: Company) {
        val pk = CompanyUserRolePK()
        pk.company = company;
        pk.user = user
        val companyUserRole = CompanyUserRole(pk)
        companyUserRole.companyRole = findCompanyRoleByName(CompanyRoleTypes.ROLE_COMPANY_ADMIN.toString());
        companyUserRoleRepository.save(companyUserRole);
    }
}