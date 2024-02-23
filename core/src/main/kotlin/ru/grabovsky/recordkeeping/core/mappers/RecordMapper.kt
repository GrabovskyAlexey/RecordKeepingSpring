package ru.grabovsky.recordkeeping.core.mappers

import org.mapstruct.Mapper
import ru.grabovsky.recordkeeping.api.dto.record.RecordDto
import ru.grabovsky.recordkeeping.core.entity.db.Record

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 16.02.2024 22:50
 */

@Mapper
interface RecordMapper {
    fun fromEntityToDto(record: Record): RecordDto
    fun fromDtoToEntity(dto: RecordDto): Record
}