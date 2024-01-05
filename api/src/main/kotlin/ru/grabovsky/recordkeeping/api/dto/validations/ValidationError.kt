package ru.grabovsky.recordkeeping.api.dto.validations

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация об ошибках валидации", name = "Ошибки валидации")
data class ValidationError(
    @Schema(description = "Имя поля в котором возникла ошибка", example = "fieldName")
    @JsonProperty("fieldName")
    val fieldName: String,
    @Schema(description = "Сообщение об ошибке", example = "Field Name must not be null")
    @JsonProperty("message")
    val message: String
)
