package ru.grabovsky.recordkeeping.api.dto.record

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
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
@Schema(description = "Запись о входящей\\исходящей корреспонденции", name = "Record")
data class RecordDto(
    @Schema(description = "id записи", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Направление не может быть пустым")
    @Schema(description = "Направление: входящее, исходящее, специальное", example = "IN")
    @JsonProperty("direction")
    var direction: Direction,
    @Size(min = 7, max = 7, message = "HEX представление цвета должно быть 7 символов")
    @Schema(description = "HEX представление цвета для выделения сообщения", example = "#FF0000")
    @JsonProperty("color")
    var color: String = "#FFFFFF",
    @NotEmpty(message = "Регистрационный номер сообщения не может быть пустым")
    @Size(min = 2, max = 50, message = "Регистрационный номер сообщения должен быть от 2 до 50 символов")
    @Schema(description = "Регистрационный номер сообщения", example = "1-ВН-47\\32")
    @JsonProperty("mailNumber")
    var mailNumber: String,
    @NotEmpty(message = "Дата регистрации сообщения не может быть пустой")
    @Schema(description = "Дата регистрации сообщения")
    @JsonProperty("regDate")
    var regDate: LocalDate,
    @Size(min = 2, max = 250, message = "Тема сообщения должен быть от 2 до 250 символов")
    @Schema(description = "Тема сообщения", example = "Замечания по результатам испытаний")
    @JsonProperty("title")
    var title: String,
    @Schema(description = "Список ответных сообщений на данное")
    @JsonProperty("reply")
    var reply: Set<RecordDto> = setOf(),
    @Schema(description = "Сообщение, ответом на которое является данное")
    @JsonProperty("replyTo")
    var replyTo: RecordDto? = null,
    @NotEmpty(message = "Дата получения сообщения не может быть пустой")
    @Schema(description = "Дата получения сообщения")
    @JsonProperty("mailDate")
    var mailDate: LocalDate,
    @Schema(description = "Краткое содержание сообщения")
    @JsonProperty("description")
    var description: String? = null,
    @Schema(description = "Проект с которым связана запись")
    @JsonProperty("project")
    var project: ProjectDto? = null,
    @Schema(description = "Сотрудник с которым связана запись")
    @JsonProperty("employee")
    var employee: EmployeeDto? = null,
    @Schema(description = "Контрагент с которым связана запись")
    @JsonProperty("contractor")
    var contractor: ContractorDto? = null,
    @NotEmpty(message = "У сообщения не может отсутствовать компания")
    @Schema(description = "Компания с которой связано сообщение")
    @JsonProperty("company")
    var company: CompanyDto,
    @NotEmpty(message = "У сообщения обязательно должен быть указан пользователь который его зарегистрировал")
    @Schema(description = "Зарегистрировавший сообщение")
    @JsonProperty("author")
    var author: UserDto,
    @Schema(description = "Список файлов")
    @JsonProperty("files")
    val files: List<FileDto> = listOf()
)