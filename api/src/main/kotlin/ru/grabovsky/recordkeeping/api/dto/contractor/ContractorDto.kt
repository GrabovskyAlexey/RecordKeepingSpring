package ru.grabovsky.recordkeeping.api.dto.contractor

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:14
 */
@Schema(description = "Контрагент", name = "Contractor")
data class ContractorDto(
    @Schema(description = "id контрагента", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Название контрагента не может быть пустым")
    @Size(min = 3, max = 100, message = "Название контрагента должно быть от 3 до 100 символов")
    @Schema(description = "Название контрагента", example = "ООО \"Ромашка\"")
    @JsonProperty("contractorName")
    val name: String,
    @NotEmpty(message = "У контрагента не может отсутствовать компания")
    @Schema(description = "Компания с которой связан контрагент")
    @JsonProperty("company")
    val company: CompanyDto,
    @Schema(description = "Список записей")
    @JsonProperty("records")
    val records: List<Record> = listOf()
)