package ru.grabovsky.recordkeeping.api.dto.file

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 13:41
 */
@Schema(description = "swagger.file.info", name = "File")
data class FileDto(
    @Schema(description = "swagger.file.id", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @field:NotEmpty(message = "{validation.file.path.nonEmpty}")
    @field:Size(min = 3, max = 250, message = "{validation.file.path.size}")
    @Schema(description = "swagger.file.path", example = "C:\\")
    @JsonProperty("filePath")
    var path: String,
    @field:NotEmpty(message = "{validation.file.name.nonEmpty}")
    @field:Size(min = 3, max = 250, message = "{validation.file.name.size}")
    @Schema(description = "swagger.file.name", example = "1.txt")
    @JsonProperty("fileName")
    var filename: String
)