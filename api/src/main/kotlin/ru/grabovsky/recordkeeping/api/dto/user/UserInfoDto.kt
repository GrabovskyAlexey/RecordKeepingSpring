package ru.grabovsky.recordkeeping.api.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import java.time.LocalDate

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:38
 */
@Schema(description = "swagger.user.userInfo", name = "UserInfo")
data class UserInfoDto(
    @field:Size(min = 3, max = 250, message = "{validation.userInfo.name.size}")
    @Schema(description = "swagger.userInfo.name", example = "user")
    @JsonProperty("name")
    var name: String? = null,
    @field:Size(min = 3, max = 250, message = "{validation.userInfo.surname.size}")
    @Schema(description = "swagger.userInfo.surname", example = "user")
    @JsonProperty("surname")
    var surname: String? = null,
    // TODO Сделать более точную валидацию телефона
    @field:Size(min = 3, max = 20, message = "{validation.userInfo.phone.size}")
    @Schema(description = "swagger.userInfo.phone", example = "+79991234567")
    @JsonProperty("phone")
    var phone: String? = null,
    @Schema(description = "swagger.userInfo.city", example = "Москва")
    @JsonProperty("city")
    var city: String? = null,
    @Schema(description = "swagger.userInfo.birthday")
    @JsonProperty("birthday")
    var birthday: LocalDate? = null,
    @Schema(description = "swagger.userInfo.regDate")
    @JsonProperty("regDate")
    var regDate: LocalDate
)