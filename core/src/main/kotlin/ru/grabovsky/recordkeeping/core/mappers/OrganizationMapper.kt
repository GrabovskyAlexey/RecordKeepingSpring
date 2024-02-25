package ru.grabovsky.recordkeeping.core.mappers

import org.mapstruct.Mapper
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationShortInfoDto
import ru.grabovsky.recordkeeping.core.entity.db.Organization

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 22:50
 */

@Mapper(uses = [
    RecordMapper::class,
    EmployeeMapper::class,
    ContractorsMapper::class,
    ProjectMapper::class
])
interface OrganizationMapper {
    fun fromEntityToShortDto(organization: Organization): OrganizationShortInfoDto
    fun fromShortDtoToEntity(dto: OrganizationShortInfoDto): Organization
    fun fromDtoToEntity(dto: OrganizationDto): Organization
    fun fromEntityToDto(organization: Organization) : OrganizationDto

}