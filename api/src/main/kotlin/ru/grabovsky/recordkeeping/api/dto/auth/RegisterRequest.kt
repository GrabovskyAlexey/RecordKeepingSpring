package ru.grabovsky.recordkeeping.api.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Schema(description = "swagger.register.request", name = "RegisterRequest")
data class RegisterRequest(
    @field:NotEmpty(message = "{validation.username.nonEmpty}")
    @field:Size(min = 3, max = 20, message = "{validation.username.size}")
    @Schema(description = "swagger.user.username", example = "user")
    @JsonProperty("username")
    val username: String,

    @field:NotEmpty(message = "{validation.password.nonEmpty}")
    @field:Size(min = 8, message = "{validation.password.length}")
    @Schema(description = "swagger.user.password", example = "pa$\$w0rd")
    @JsonProperty("password")
    val password: String,

    @field:NotEmpty(message = "{validation.email.nonEmpty}")
    @field:Email(message = "{validation.email.incorrect}")
    @Schema(description = "swagger.user.email", example = "user@example.com")
    @JsonProperty("email")
    val email: String
)

