package ru.grabovsky.recordkeeping.api.dto.invite

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:26
 */
@Schema(description = "Приглашение", name = "Invite")
data class InviteDto(
    @Schema(description = "id приглашения", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Введите корректный адрес электронной почты")
    @Schema(description = "Адрес электронной почты", example = "user@example.com")
    @JsonProperty("email")
    var email: String,
    @NotEmpty(message = "Код приглашения не может быть пустым")
    @Size(min = 3, max = 64, message = "Код приглашения должен быть от 3 до 64 символов")
    @Schema(description = "Код приглашения", example = "9a11f5b5-73cc-4fca-96c3-d5be090032a4")
    @JsonProperty("inviteCode")
    var inviteCode: String,
    @NotEmpty(message = "У приглашения не может отсутствовать компания")
    @Schema(description = "Компания с которой связано приглашение")
    @JsonProperty("company")
    var company: CompanyDto
)