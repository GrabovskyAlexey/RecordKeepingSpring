package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationInfoResponseDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationShortInfoDto
import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.core.entity.db.Organization
import ru.grabovsky.recordkeeping.core.exceptions.ForbiddenOperationException
import ru.grabovsky.recordkeeping.core.exceptions.NotFoundException
import ru.grabovsky.recordkeeping.core.mappers.OrganizationMapper
import ru.grabovsky.recordkeeping.core.repositories.db.OrganizationRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.*
import java.security.Principal

@Service
class OrganizationServiceImpl(
    private val organizationMapper: OrganizationMapper,
    private val organizationRepository: OrganizationRepository,
    private val authorizationService: AuthorizationService,
    private val userService: UserService,
    private val organizationRoleService: OrganizationRoleService,
    private val localizationService: LocalizationService
) : OrganizationService {
    override fun getAllOrganization(principal: Principal): OrganizationInfoResponseDto {
        if (authorizationService.isAdmin(principal)) {
            return OrganizationInfoResponseDto(
                organizationRepository.findAll()
                    .map { organizationMapper.fromEntityToShortDto(it) }
                    .toList())
        }
        val user = userService.getUserByUsername(principal.name)
        return OrganizationInfoResponseDto(
            organizationRepository.findAllByUsersContains(user)
                .map { organizationMapper.fromEntityToShortDto(it) }
                .toList())
    }

    override fun getOrganizationById(id: Long, principal: Principal): OrganizationDto {
        val user = userService.getUserByUsername(principal.name)
        if (authorizationService.isAdmin(principal) || authorizationService.userHasAuthority(
                user,
                organizationId = id,
                authority = AuthorityTypes.ADD_RECORD
            )
        ) {
            val organization = findById(id)
            return organizationMapper.fromEntityToDto(organization)
        }
        throw ForbiddenOperationException()
    }

    override fun createOrganization(organizationDto: OrganizationShortInfoDto, principal: Principal): OrganizationShortInfoDto {
        if (!authorizationService.isActivatedUser(principal)) {
            throw ForbiddenOperationException()
        }
        val organization = Organization()
        organization.name = organizationDto.name
        organization.isEnabled = true;
        organizationRepository.save(organization)
        val user = userService.getUserByUsername(principal.name);
        organizationRoleService.addUserAdminToOrganization(user, organization)
        return organizationMapper.fromEntityToShortDto(organization)
    }

    override fun updateOrganization(id: Long, organizationDto: OrganizationShortInfoDto, principal: Principal) {
        val user = userService.getUserByUsername(principal.name)
        val hasAuthority =
            authorizationService.userHasAuthority(user, organizationId = id, authority = AuthorityTypes.EDIT_ORGANIZATION)
        // TODO Возможно стоит вынести в отдельный сервис проверка на наличие прав или админский доступ
        if (!hasAuthority || !authorizationService.isAdmin(principal)) {
            throw ForbiddenOperationException()
        }
        val organization = findById(id)
        organization.name = organizationDto.name
        // TODO Проверить нужно ли сетить isEnabled
        organizationRepository.save(organization)
    }

    override fun deleteOrganization(id: Long, principal: Principal) {
        if (!authorizationService.isAdmin(principal)) {
            throw ForbiddenOperationException()
        }
        val organization = findById(id)
        organization.isEnabled = false;
        organizationRepository.save(organization);
    }

    private fun findById(id: Long): Organization = organizationRepository.findById(id).orElseThrow {
        NotFoundException(localizationService.getErrorMessage("organization.notFound"))
    }
}