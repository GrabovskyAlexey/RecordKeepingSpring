package ru.grabovsky.recordkeeping.api.dto.company

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 21.01.2024
 */

@Schema(description = "Список организаций", name = "CompanyList")
data class CompanyResponseDto(
    @Schema(description = "Список организаций")
    @JsonProperty("companies")
    val users: List<CompanyDto> = listOf()
)