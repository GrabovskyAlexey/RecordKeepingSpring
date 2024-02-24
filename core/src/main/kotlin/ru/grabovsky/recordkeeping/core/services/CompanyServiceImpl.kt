package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyInfoResponseDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyShortInfoDto
import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.core.entity.db.Company
import ru.grabovsky.recordkeeping.core.exceptions.ForbiddenOperationException
import ru.grabovsky.recordkeeping.core.exceptions.NotFoundException
import ru.grabovsky.recordkeeping.core.mappers.CompanyMapper
import ru.grabovsky.recordkeeping.core.repositories.db.CompanyRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.*
import java.security.Principal

@Service
class CompanyServiceImpl(
    private val companyMapper: CompanyMapper,
    private val companyRepository: CompanyRepository,
    private val authorizationService: AuthorizationService,
    private val userService: UserService,
    private val companyRoleService: CompanyRoleService,
    private val localizationService: LocalizationService
) : CompanyService {
    override fun getAllCompany(principal: Principal): CompanyInfoResponseDto {
        if (authorizationService.isAdmin(principal)) {
            return CompanyInfoResponseDto(
                companyRepository.findAll()
                    .map { companyMapper.fromEntityToShortDto(it) }
                    .toList())
        }
        val user = userService.getUserByUsername(principal.name)
        return CompanyInfoResponseDto(
            companyRepository.findAllByUsersContains(user)
                .map { companyMapper.fromEntityToShortDto(it) }
                .toList())
    }

    override fun getCompanyById(id: Long, principal: Principal): CompanyDto {
        val user = userService.getUserByUsername(principal.name)
        if (authorizationService.isAdmin(principal) || authorizationService.userHasAuthority(
                user,
                companyId = id,
                authority = AuthorityTypes.ADD_RECORD
            )
        ) {
            val company = findById(id)
            return companyMapper.fromEntityToDto(company)
        }
        throw ForbiddenOperationException()
    }

    override fun createCompany(companyDto: CompanyShortInfoDto, principal: Principal): CompanyShortInfoDto {
        if (!authorizationService.isActivatedUser(principal)) {
            throw ForbiddenOperationException()
        }
        val company = Company()
        company.name = companyDto.name
        company.isEnabled = true;
        companyRepository.save(company)
        val user = userService.getUserByUsername(principal.name);
        companyRoleService.addUserAdminToCompany(user, company)
        return companyMapper.fromEntityToShortDto(company)
    }

    override fun updateCompany(id: Long, companyDto: CompanyShortInfoDto, principal: Principal) {
        val user = userService.getUserByUsername(principal.name)
        val hasAuthority =
            authorizationService.userHasAuthority(user, companyId = id, authority = AuthorityTypes.EDIT_COMPANY)
        // TODO Возможно стоит вынести в отдельный сервис проверка на наличие прав или админский доступ
        if (!hasAuthority || !authorizationService.isAdmin(principal)) {
            throw ForbiddenOperationException()
        }
        val company = findById(id)
        company.name = companyDto.name
        // TODO Проверить нужно ли сетить isEnabled
        companyRepository.save(company)
    }

    override fun deleteCompany(id: Long, principal: Principal) {
        if (!authorizationService.isAdmin(principal)) {
            throw ForbiddenOperationException()
        }
        val company = findById(id)
        company.isEnabled = false;
        companyRepository.save(company);
    }

    private fun findById(id: Long): Company = companyRepository.findById(id).orElseThrow {
        NotFoundException(localizationService.getErrorMessage("company.notFound"))
    }
}