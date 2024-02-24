package ru.grabovsky.recordkeeping.api.dto.company

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 21.01.2024
 */

@Schema(description = "swagger.company.list", name = "CompanyInfoList")
data class CompanyInfoResponseDto(
    @Schema(description = "swagger.company.list")
    @JsonProperty("companies")
    val users: List<CompanyShortInfoDto> = listOf()
)