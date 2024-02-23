package ru.grabovsky.recordkeeping.core.mappers

import org.mapstruct.Mapper
import ru.grabovsky.recordkeeping.api.dto.employee.EmployeeDto
import ru.grabovsky.recordkeeping.core.entity.db.Employee

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 16.02.2024 22:50
 */

@Mapper
interface EmployeeMapper {
    fun fromEntityToDto(employee: Employee): EmployeeDto
    fun fromDtoToEntity(dto: EmployeeDto): Employee
}