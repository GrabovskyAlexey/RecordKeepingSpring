package ru.grabovsky.recordkeeping.api.dto.record

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import ru.grabovsky.recordkeeping.api.dto.contractor.ContractorDto
import ru.grabovsky.recordkeeping.api.dto.employee.EmployeeDto
import ru.grabovsky.recordkeeping.api.dto.file.FileDto
import ru.grabovsky.recordkeeping.api.dto.project.ProjectDto
import ru.grabovsky.recordkeeping.api.dto.user.UserDto
import ru.grabovsky.recordkeeping.api.types.Direction
import java.time.LocalDate
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:28
 */
@Schema(description = "swagger.record.info", name = "Record")
data class RecordDto(
    @Schema(description = "swagger.record.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.record.direction.nonEmpty}")
    @Schema(description = "swagger.record.direction", example = "IN")
    @JsonProperty("direction")
    var direction: Direction,
    @field:Size(min = 7, max = 7, message = "{validation.record.color.size}")
    @Schema(description = "swagger.record.color", example = "#FF0000")
    @JsonProperty("color")
    var color: String = "#FFFFFF",
    @field:NotEmpty(message = "{validation.record.number.nonEmpty}")
    @field:Size(min = 2, max = 50, message = "{validation.record.number.size}")
    @Schema(description = "swagger.record.number", example = "1-ВН-47\\32")
    @JsonProperty("mailNumber")
    var mailNumber: String,
    @field:NotEmpty(message = "{validation.record.regDate.nonEmpty}")
    @Schema(description = "swagger.record.regDate")
    @JsonProperty("regDate")
    var regDate: LocalDate,
    @field:Size(min = 2, max = 250, message = "{validation.record.subject.size}")
    @Schema(description = "swagger.record.subject", example = "Замечания по результатам испытаний")
    @JsonProperty("subject")
    var subject: String,
    @Schema(description = "swagger.record.reply")
    @JsonProperty("reply")
    var reply: Set<RecordDto> = setOf(),
    @Schema(description = "swagger.record.replyTo")
    @JsonProperty("replyTo")
    var replyTo: RecordDto? = null,
    @field:NotEmpty(message = "{validation.record.mailDate.nonEmpty}")
    @Schema(description = "swagger.record.mailDate")
    @JsonProperty("mailDate")
    var mailDate: LocalDate,
    @Schema(description = "swagger.record.description")
    @JsonProperty("description")
    var description: String? = null,
    @Schema(description = "swagger.record.project")
    @JsonProperty("project")
    var project: ProjectDto? = null,
    @Schema(description = "swagger.record.employee")
    @JsonProperty("employee")
    var employee: EmployeeDto? = null,
    @Schema(description = "swagger.record.contractor")
    @JsonProperty("contractor")
    var contractor: ContractorDto? = null,
    @field:NotEmpty(message = "{validation.record.organization.nonEmpty}")
    @Schema(description = "swagger.record.organization")
    @JsonProperty("organization")
    var organization: OrganizationDto,
    @field:NotEmpty(message = "{validation.record.author.nonEmpty}")
    @Schema(description = "swagger.record.author")
    @JsonProperty("author")
    var author: UserDto,
    @Schema(description = "swagger.file.list")
    @JsonProperty("files")
    val files: List<FileDto> = listOf()
)