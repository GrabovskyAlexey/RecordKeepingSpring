package ru.grabovsky.recordkeeping.api.dto.project

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import ru.grabovsky.recordkeeping.api.dto.record.RecordDto
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:27
 */
@Schema(description = "swagger.project.info", name = "Project")
data class ProjectDto(
    @Schema(description = "swagger.project.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.project.short.nonEmpty}")
    @field:Size(min = 3, max = 15, message = "{validation.project.short.size}")
    @Schema(description = "swagger.project.shortName", example = "НП1")
    @JsonProperty("shortProjectName")
    var shortName: String,
    @field:Size(min = 3, max = 250, message = "{validation.project.full.size}")
    @Schema(description = "swagger.project.fullName", example = "Новый проект №1")
    @JsonProperty("fullProjectName")
    var fullName: String,
    @Schema(description = "swagger.project.description", example = "Это краткое описание проекта НП1")
    @JsonProperty("projectDescription")
    var description: String,
    @field:NotEmpty(message = "{validation.project.organization.nonEmpty}")
    @Schema(description = "swagger.project.organization")
    @JsonProperty("organization")
    var organization: OrganizationDto,
    @Schema(description = "swagger.project.list")
    @JsonProperty("records")
    val records: List<RecordDto> = listOf()
)