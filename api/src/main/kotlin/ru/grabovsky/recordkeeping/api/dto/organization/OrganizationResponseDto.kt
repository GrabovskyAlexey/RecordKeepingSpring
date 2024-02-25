package ru.grabovsky.recordkeeping.api.dto.organization

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 21.01.2024
 */

@Schema(description = "swagger.organization.list", name = "CompanyList")
data class OrganizationResponseDto(
    @Schema(description = "swagger.organization.list")
    @JsonProperty("organizations")
    val users: List<OrganizationDto> = listOf()
)