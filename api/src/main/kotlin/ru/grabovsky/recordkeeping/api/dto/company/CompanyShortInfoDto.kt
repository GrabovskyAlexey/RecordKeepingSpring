package ru.grabovsky.recordkeeping.api.dto.company

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 14.01.2024
 */

@Schema(description = "Краткая информация по организации", name = "CompanyShortInfo")
data class CompanyShortInfoDto(
    @Schema(description = "id организации", example = "1")
    @JsonProperty("id")
    val id: Long? = null,
    @NotEmpty(message = "Название организации не может быть пустым")
    @Size(min = 3, max = 250, message = "Название организации должно быть от 3 до 250 символов")
    @Schema(description = "Название организации", example = "ООО \"Ромашка\"")
    @JsonProperty("companyName")
    val name: String,
    @Schema(description = "Организация действующая?", example = "true")
    @JsonProperty("isEnabled")
    val isEnabled: Boolean = false)