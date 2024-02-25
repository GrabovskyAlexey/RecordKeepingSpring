package ru.grabovsky.recordkeeping.api.dto.invite

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:26
 */
@Schema(description = "swagger.invite.info", name = "Invite")
data class InviteDto(
    @Schema(description = "swagger.invite.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.email.nonEmpty}")
    @field:Email(message = "{validation.email.incorrect}")
    @Schema(description = "swagger.user.email", example = "user@example.com")
    @JsonProperty("email")
    var email: String,
    @field:NotEmpty(message = "{validation.invite.code.nonEmpty}")
    @field:Size(min = 3, max = 64, message = "{validation.invite.code.size}")
    @Schema(description = "swagger.invite.code", example = "9a11f5b5-73cc-4fca-96c3-d5be090032a4")
    @JsonProperty("inviteCode")
    var inviteCode: String,
    @field:NotEmpty(message = "{validation.invite.organization.nonEmpty}")
    @Schema(description = "swagger.invite.organization")
    @JsonProperty("organization")
    var organization: OrganizationDto
)