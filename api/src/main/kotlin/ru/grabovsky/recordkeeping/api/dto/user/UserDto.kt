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
@Schema(description = "Пользователь", name = "User")
data class UserDto(
    @Schema(description = "id пользователя", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(
        min = 3,
        max = 20,
        message = "Имя пользователя должно содержать от 3 до 20 символов"
    )
    @Schema(description = "Имя пользователя", example = "user")
    @JsonProperty("username")
    var username: String,
    @NotEmpty(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Введите корректный адрес электронной почты")
    @Schema(description = "Адрес электронной почты", example = "user@example.com")
    @JsonProperty("email")
    var email: String,
    @Schema(description = "Пользователь активен?", example = "true")
    @JsonProperty("isEnabled")
    var isEnabled: Boolean = false,
    @Schema(description = "Пользователь подтвердил адрес электронной почты?", example = "true")
    @JsonProperty("isActivated")
    var isActivated: Boolean = false,
    @Schema(description = "Дополнительная информация о пользователе")
    @JsonProperty("userInfo")
    var userInfo: UserInfoDto
)