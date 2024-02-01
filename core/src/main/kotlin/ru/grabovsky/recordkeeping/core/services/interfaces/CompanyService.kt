package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyInfoResponseDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyShortInfoDto
import java.security.Principal

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 21.01.2024 15:28
 */
interface CompanyService {
    fun getAllCompany(principal: Principal): CompanyInfoResponseDto
    fun getCompanyById(id: Long, principal: Principal): CompanyDto
    fun createCompany(companyDto: CompanyShortInfoDto, principal: Principal): CompanyShortInfoDto
    fun updateCompany(id: Long, companyDto: CompanyShortInfoDto, principal: Principal)
    fun deleteCompany(id: Long, principal: Principal)
}