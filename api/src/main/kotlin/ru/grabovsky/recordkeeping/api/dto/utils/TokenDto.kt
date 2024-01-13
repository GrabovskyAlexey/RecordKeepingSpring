package ru.grabovsky.recordkeeping.api.dto.utils

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "Токен подтверждения различных действий", name = "Token")
data class TokenDto(
    @Schema(description = "Токен подтверждения различных действий")
    @JsonProperty("token")
    val token: String
)
