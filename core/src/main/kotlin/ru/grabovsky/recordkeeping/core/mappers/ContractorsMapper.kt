package ru.grabovsky.recordkeeping.core.mappers

import org.mapstruct.Mapper
import ru.grabovsky.recordkeeping.api.dto.contractor.ContractorDto
import ru.grabovsky.recordkeeping.core.entity.db.Contractor

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 16.02.2024 22:50
 */

@Mapper
interface ContractorsMapper {
    fun fromEntityToDto(contractor: Contractor): ContractorDto
    fun fromDtoToEntity(dto: ContractorDto): Contractor
}