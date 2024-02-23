package ru.grabovsky.recordkeeping.api.dto.employee

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import ru.grabovsky.recordkeeping.api.dto.record.RecordDto
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:16
 */
@Schema(description = "Сотрудник", name = "Employee")
data class EmployeeDto(
    @Schema(description = "id сотрудника", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Имя сотрудника не может быть пустым")
    @Size(min = 6, message = "Название контрагента должно быть от 6 до 100 символов")
    @Schema(description = "Имя сотрудника", example = "Иванов И.И.")
    @JsonProperty("employeeName")
    val name: String,
    @NotEmpty(message = "У сотрудника не может отсутствовать компания")
    @Schema(description = "Компания с которой связан сотрудник")
    @JsonProperty("company")
    val company: CompanyDto,
    @Schema(description = "Список записей")
    @JsonProperty("records")
    val records: List<RecordDto> = listOf()
)