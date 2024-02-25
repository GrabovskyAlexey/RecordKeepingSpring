package ru.grabovsky.recordkeeping.api.dto.organization

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

@Schema(description = "swagger.organization.info.short", name = "CompanyShortInfo")
data class OrganizationShortInfoDto(
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
    val isEnabled: Boolean = false)