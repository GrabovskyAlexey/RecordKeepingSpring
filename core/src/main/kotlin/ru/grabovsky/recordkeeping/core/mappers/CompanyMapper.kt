package ru.grabovsky.recordkeeping.core.mappers

import org.mapstruct.Mapper
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyShortInfoDto
import ru.grabovsky.recordkeeping.core.entity.db.Company

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
interface CompanyMapper {
    fun fromEntityToShortDto(company: Company): CompanyShortInfoDto
    fun fromShortDtoToEntity(dto: CompanyShortInfoDto): Company
    fun fromDtoToEntity(dto: CompanyDto): Company
    fun fromEntityToDto(company: Company) : CompanyDto

}