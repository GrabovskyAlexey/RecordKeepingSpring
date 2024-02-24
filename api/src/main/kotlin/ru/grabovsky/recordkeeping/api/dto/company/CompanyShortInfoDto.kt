package ru.grabovsky.recordkeeping.api.dto.company

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 14.01.2024
 */

@Schema(description = "swagger.company.info.short", name = "CompanyShortInfo")
data class CompanyShortInfoDto(
    @Schema(description = "swagger.company.id", example = "1")
    @JsonProperty("id")
    val id: Long? = null,
    @field:NotEmpty(message = "{validation.company.nonEmpty}")
    @field:Size(min = 3, max = 250, message = "{validation.company.size}")
    @Schema(description = "swagger.company.name", example = "ООО \"Ромашка\"")
    @JsonProperty("companyName")
    val name: String,
    @Schema(description = "swagger.company.isEnabled", example = "true")
    @JsonProperty("isEnabled")
    val isEnabled: Boolean = false)