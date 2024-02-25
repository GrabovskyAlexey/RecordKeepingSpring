package ru.grabovsky.recordkeeping.api.dto.organization

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.contractor.ContractorDto
import ru.grabovsky.recordkeeping.api.dto.employee.EmployeeDto
import ru.grabovsky.recordkeeping.api.dto.invite.InviteDto
import ru.grabovsky.recordkeeping.api.dto.project.ProjectDto
import ru.grabovsky.recordkeeping.api.dto.record.RecordDto
import ru.grabovsky.recordkeeping.api.dto.user.UserDto
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 14.01.2024
 */

@Schema(description = "swagger.organization.info.full", name = "Company")
data class OrganizationDto(
    @Schema(description = "swagger.organization.id", example = "1")
    @JsonProperty("id")
    val id: Long? = null,
    @field:NotEmpty(message = "{validation.organization.nonEmpty}")
    @field:Size(min = 3, max = 250, message = "{validation.organization.size}")
    @Schema(description = "swagger.organization.name", example = "ООО \"Ромашка\"")
    @JsonProperty("organizationName")
    val name: String,
    @Schema(description = "swagger.organization.isEnabled", example = "true")
    @JsonProperty("isEnabled")
    val isEnabled: Boolean = false,
    @Schema(description = "swagger.contractor.list")
    @JsonProperty("contractors")
    val contractors: List<ContractorDto> = listOf(),
    @Schema(description = "swagger.employee.list")
    @JsonProperty("employees")
    val employees: List<EmployeeDto> = listOf(),
    @Schema(description = "swagger.invite.list")
    @JsonProperty("invites")
    val invites: List<InviteDto> = listOf(),
    @Schema(description = "swagger.project.list")
    @JsonProperty("projects")
    val projects: List<ProjectDto> = listOf(),
    @Schema(description = "swagger.record.list")
    @JsonProperty("records")
    val records: List<RecordDto> = listOf(),
    @Schema(description = "swagger.user.list")
    @JsonProperty("users")
    val users: List<UserDto> = listOf()
)