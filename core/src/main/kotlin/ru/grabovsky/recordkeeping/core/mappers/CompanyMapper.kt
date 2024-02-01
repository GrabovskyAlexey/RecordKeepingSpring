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

@Mapper
interface CompanyMapper {
    fun fromEntityToDto(company: Company) : CompanyDto
    fun fromEntityToShortDto(company: Company) : CompanyShortInfoDto
    fun fromDtoToEntity(dto: CompanyDto): Company
    fun fromShortDtoToEntity(dto: CompanyShortInfoDto): Company
}