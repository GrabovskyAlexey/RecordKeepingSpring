package ru.grabovsky.recordkeeping.core.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grabovsky.recordkeeping.api.dto.company.CompanyShortInfoDto
import ru.grabovsky.recordkeeping.core.annotations.secutiry.AllowActivatedUser
import ru.grabovsky.recordkeeping.core.controllers.interfaces.CompanyController
import ru.grabovsky.recordkeeping.core.services.interfaces.CompanyService
import java.security.Principal

@RestController
@RequestMapping("\${application.api.url}/company")
class CompanyControllerImpl(
    private val companyService: CompanyService
) : CompanyController {
    @AllowActivatedUser
    override fun getAllCompany(principal: Principal) = companyService.getAllCompany(principal)
    @AllowActivatedUser
    override fun getCompanyById(id: Long, principal: Principal) = companyService.getCompanyById(id, principal)
    @AllowActivatedUser
    override fun createCompany(company: CompanyShortInfoDto, principal: Principal) = companyService.createCompany(company, principal)
    @AllowActivatedUser
    override fun updateCompany(id: Long, company: CompanyShortInfoDto, principal: Principal) = companyService.updateCompany(id, company, principal)
    @AllowActivatedUser
    override fun deleteCompany(id: Long, principal: Principal) = companyService.deleteCompany(id, principal)
}