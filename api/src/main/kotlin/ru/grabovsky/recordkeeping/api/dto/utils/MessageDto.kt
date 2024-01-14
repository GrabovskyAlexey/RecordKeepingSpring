package ru.grabovsky.recordkeeping.api.dto.utils

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "Информационное сообщение или сообщение об ошибке", name = "Message")
data class MessageDto(
    @Schema(description = "Информационное сообщение или сообщение об ошибке")
    @JsonProperty("message")
    val message: String
) {

    @Schema(description = "Дата и время сообщения")
    @JsonProperty("date")
    val date: Date = Date()
}
