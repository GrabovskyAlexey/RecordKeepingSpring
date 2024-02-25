package ru.grabovsky.recordkeeping.api.dto.employee

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
 * @created 14.01.2024 13:16
 */
@Schema(description = "swagger.employee.info", name = "Employee")
data class EmployeeDto(
    @Schema(description = "swagger.employee.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.employee.nonEmpty}")
    @field:Size(min = 6, max = 100, message = "{validation.employee.organization.nonEmpty}")
    @Schema(description = "swagger.employee.name", example = "Иванов И.И.")
    @JsonProperty("employeeName")
    val name: String,
    @field:NotEmpty(message = "{validation.employee.organization.nonEmpty}")
    @Schema(description = "swagger.employee.organization")
    @JsonProperty("organization")
    val organization: OrganizationDto,
    @Schema(description = "swagger.record.list")
    @JsonProperty("records")
    val records: List<RecordDto> = listOf()
)