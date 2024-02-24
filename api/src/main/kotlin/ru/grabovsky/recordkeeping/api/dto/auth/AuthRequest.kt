package ru.grabovsky.recordkeeping.api.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 03.01.2024
 */

@Schema(description = "swagger.auth.request", name = "AuthRequest")
data class AuthRequest(
    @field:NotEmpty(message = "{validation.username.nonEmpty}")
    @Schema(description = "swagger.user.username", example = "user")
    @JsonProperty("username")
    val username: String,

    @field:NotEmpty(message = "{validation.password.nonEmpty}")
    @field:Size(min = 8, message = "{validation.password.length}")
    @Schema(description = "swagger.user.password", example = "pa$\$w0rd")
    @JsonProperty("password")
    val password: String)
