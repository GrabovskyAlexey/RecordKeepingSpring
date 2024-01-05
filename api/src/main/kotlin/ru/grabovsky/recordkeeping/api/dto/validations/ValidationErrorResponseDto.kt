package ru.grabovsky.recordkeeping.api.dto.validations

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema(description = "List of validation error", name = "ValidationErrorResponse")
data class ValidationErrorResponseDto(
    @Schema(description = "Список ошибок валидации")
    @JsonProperty("errors")
    val errors: List<ValidationError>

) {
    @Schema(description = "Дата и время возникновения ошибок", example = "2022-09-15T19:35:49.926529Z")
    @JsonProperty("time")
    val time: Instant = Instant.now()
}
