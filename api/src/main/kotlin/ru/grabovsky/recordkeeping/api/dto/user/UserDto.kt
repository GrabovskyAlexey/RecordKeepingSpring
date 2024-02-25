package ru.grabovsky.recordkeeping.api.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:37
 */
@Schema(description = "swagger.user.info", name = "User")
data class UserDto(
    @Schema(description = "swagger.user.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.username.nonEmpty}")
    @field:Size(min = 3, max = 20, message = "{validation.username.size}")
    @Schema(description = "swagger.user.username", example = "user")
    @JsonProperty("username")
    var username: String,
    @field:NotEmpty(message = "{validation.email.nonEmpty}")
    @field:Email(message = "validation.email.incorrect")
    @Schema(description = "swagger.user.email", example = "user@example.com")
    @JsonProperty("email")
    var email: String,
    @Schema(description = "swagger.user.isEnabled", example = "true")
    @JsonProperty("isEnabled")
    var isEnabled: Boolean = false,
    @Schema(description = "swagger.user.isActivated", example = "true")
    @JsonProperty("isActivated")
    var isActivated: Boolean = false,
    @Schema(description = "swagger.user.userInfo")
    @JsonProperty("userInfo")
    var userInfo: UserInfoDto
)