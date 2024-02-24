package ru.grabovsky.recordkeeping.api.dto.contractor

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import ru.grabovsky.recordkeeping.api.dto.record.RecordDto
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:14
 */
@Schema(description = "swagger.contractor.info", name = "Contractor")
data class ContractorDto(
    @Schema(description = "swagger.contractor.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.contractor.nonEmpty}")
    @field:Size(min = 3, max = 100, message = "{validation.contractor.size}")
    @Schema(description = "swagger.contractor.name", example = "ООО \"Ромашка\"")
    @JsonProperty("contractorName")
    val name: String,
    @field:NotEmpty(message = "{validation.contractor.company.nonEmpty}")
    @Schema(description = "swagger.contractor.company")
    @JsonProperty("company")
    val company: CompanyDto,
    @Schema(description = "swagger.record.list")
    @JsonProperty("records")
    val records: List<RecordDto> = listOf()
)