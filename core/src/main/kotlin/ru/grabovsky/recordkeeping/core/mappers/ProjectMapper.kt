package ru.grabovsky.recordkeeping.core.mappers

import org.mapstruct.Mapper
import ru.grabovsky.recordkeeping.api.dto.project.ProjectDto
import ru.grabovsky.recordkeeping.core.entity.db.Project

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 16.02.2024 22:50
 */

@Mapper
interface ProjectMapper {
    fun fromEntityToDto(project: Project): ProjectDto
    fun fromDtoToEntity(dto: ProjectDto): Project
}