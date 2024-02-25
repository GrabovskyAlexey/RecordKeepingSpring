package ru.grabovsky.recordkeeping.api.dto.validations

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "swagger.validation.error", name = "ValidationError")
data class ValidationError(
    @Schema(description = "swagger.validation.error.fieldName", example = "fieldName")
    @JsonProperty("fieldName")
    val fieldName: String,
    @Schema(description = "swagger.validation.error.message", example = "Field Name must not be null")
    @JsonProperty("message")
    val message: String
)
