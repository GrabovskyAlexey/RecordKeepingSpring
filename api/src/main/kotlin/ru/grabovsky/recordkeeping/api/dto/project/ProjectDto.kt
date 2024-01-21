package ru.grabovsky.recordkeeping.api.dto.project

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:27
 */
@Schema(description = "Проект", name = "Project")
data class ProjectDto(
    @Schema(description = "id проекта", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Краткое название проекта не может быть пустым")
    @Size(min = 3, max = 15, message = "Название проекта должно быть от 3 до 15 символов")
    @Schema(description = "Краткое название проекта", example = "НП1")
    @JsonProperty("shortProjectName")
    var shortName: String,
    @Size(min = 3, max = 250, message = "Полное название проекта должно быть от 3 до 250 символов")
    @Schema(description = "Полное название проекта", example = "Новый проект №1")
    @JsonProperty("fullProjectName")
    var fullName: String,
    @Schema(description = "Описание проекта", example = "Это краткое описание проекта НП1")
    @JsonProperty("projectDescription")
    var description: String,
    @NotEmpty(message = "У проекта не может отсутствовать компания")
    @Schema(description = "Компания с которой связан проекта")
    @JsonProperty("company")
    var company: CompanyDto,
    @Schema(description = "Список записей")
    @JsonProperty("records")
    val records: List<Record> = listOf()
)