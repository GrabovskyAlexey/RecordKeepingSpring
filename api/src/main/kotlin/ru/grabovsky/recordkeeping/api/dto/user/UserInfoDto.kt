package ru.grabovsky.recordkeeping.api.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import javax.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:38
 */
@Schema(description = "Дополнительная информация о пользователе", name = "UserInfo")
data class UserInfoDto(
    @Size(
        min = 3,
        max = 250,
        message = "Реальное имя пользователя должно содержать от 3 до 250 символов"
    )
    @Schema(description = "Реальное имя пользователя", example = "user")
    @JsonProperty("name")
    var name: String? = null,
    @Size(
        min = 3,
        max = 250,
        message = "Фамилия пользователя должно содержать от 3 до 250 символов"
    )
    @Schema(description = "Фамилия пользователя", example = "user")
    @JsonProperty("surname")
    var surname: String? = null,
    // TODO Сделать более точную валидацию телефона
    @Size(
        min = 3,
        max = 20,
        message = "Телефон пользователя должен содержать от 3  до 20 символов"
    )
    @Schema(description = "Телефон пользователя", example = "+79991234567")
    @JsonProperty("phone")
    var phone: String? = null,
    @Schema(description = "Город пользователя", example = "Москва")
    @JsonProperty("city")
    var city: String? = null,
    @Schema(description = "День рождения пользователя")
    @JsonProperty("city")
    var birthday: LocalDate? = null,
    @Schema(description = "Дата регистрации пользователя")
    @JsonProperty("city")
    var regDate: LocalDate
)