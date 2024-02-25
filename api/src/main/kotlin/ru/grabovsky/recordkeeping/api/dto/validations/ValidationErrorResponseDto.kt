package ru.grabovsky.recordkeeping.api.dto.validations

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema(description = "swagger.validation.error.list", name = "ValidationErrorResponse")
data class ValidationErrorResponseDto(
    @Schema(description = "swagger.validation.error.list")
    @JsonProperty("errors")
    val errors: List<ValidationError>

) {
    @Schema(description = "swagger.validation.error.time", example = "2022-09-15T19:35:49.926529Z")
    @JsonProperty("time")
    val time: Instant = Instant.now()
}
