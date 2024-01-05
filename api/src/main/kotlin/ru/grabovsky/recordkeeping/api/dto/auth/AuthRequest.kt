package ru.grabovsky.recordkeeping.api.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 03.01.2024
 */

@Schema(description = "Запрос аутентификации", name = "AuthRequest")
data class AuthRequest(
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Schema(description = "Имя пользователя", example = "user")
    @JsonProperty("username")
    val username: String,

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 8, message = "Пароль должен быть не меньше 8 символов")
    @Schema(description = "Пароль", example = "pa$\$w0rd")
    @JsonProperty("password")
    val password: String)
