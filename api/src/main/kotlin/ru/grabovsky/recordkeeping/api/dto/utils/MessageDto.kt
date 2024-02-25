package ru.grabovsky.recordkeeping.api.dto.utils

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "swagger.message.info", name = "Message")
data class MessageDto(
    @Schema(description = "swagger.message.info", example = "Информационное сообщение")
    @JsonProperty("message")
    val message: String
) {

    @Schema(description = "swagger.message.date")
    @JsonProperty("date")
    val date: Date = Date()
}
