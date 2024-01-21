package ru.grabovsky.recordkeeping.api.dto.company

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.contractor.ContractorDto
import ru.grabovsky.recordkeeping.api.dto.employee.EmployeeDto
import ru.grabovsky.recordkeeping.api.dto.invite.InviteDto
import ru.grabovsky.recordkeeping.api.dto.project.ProjectDto
import ru.grabovsky.recordkeeping.api.dto.user.UserDto
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 14.01.2024
 */

@Schema(description = "Компания", name = "Company")
data class CompanyDto(
    @Schema(description = "id компании", example = "1")
    @JsonProperty("id")
    val id: Long? = null,
    @NotEmpty(message = "Название компании не может быть пустым")
    @Size(min = 3, max = 250, message = "Название компании должно быть от 3 до 250 символов")
    @Schema(description = "Название компании", example = "ООО \"Ромашка\"")
    @JsonProperty("companyName")
    val name: String,
    @Schema(description = "Компания действующая?", example = "true")
    @JsonProperty("isEnabled")
    val isEnabled: Boolean = false,
    @Schema(description = "Список контрагентов")
    @JsonProperty("contractors")
    val contractors: List<ContractorDto> = listOf(),
    @Schema(description = "Список сотрудников")
    @JsonProperty("employees")
    val employees: List<EmployeeDto> = listOf(),
    @Schema(description = "Список приглашений")
    @JsonProperty("invites")
    val invites: List<InviteDto> = listOf(),
    @Schema(description = "Список проектов")
    @JsonProperty("projects")
    val projects: List<ProjectDto> = listOf(),
    @Schema(description = "Список записей")
    @JsonProperty("records")
    val records: List<Record> = listOf(),
    @Schema(description = "Список пользователей")
    @JsonProperty("users")
    val users: List<UserDto> = listOf()
)