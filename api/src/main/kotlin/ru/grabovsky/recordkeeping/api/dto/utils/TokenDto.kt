package ru.grabovsky.recordkeeping.api.dto.utils

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "swagger.token.confirm", name = "Token")
data class TokenDto(
    @Schema(description = "swagger.token.confirm")
    @JsonProperty("token")
    val token: String
)
