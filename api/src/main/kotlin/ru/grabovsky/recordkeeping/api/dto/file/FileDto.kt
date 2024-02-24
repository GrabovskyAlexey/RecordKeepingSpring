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
@Schema(description = "Файл", name = "File")
data class FileDto(
    @Schema(description = "id файла", example = "1")
    @JsonProperty("id")
    var id: Long? = null,
    @NotEmpty(message = "Путь к файлу не может быть пустым")
    @Size(min = 3, max = 250, message = "Путь к файлу должен быть не меньше от 3 до 250 символов")
    @Schema(description = "Путь к файлу", example = "C:\\")
    @JsonProperty("filePath")
    var path: String,
    @NotEmpty(message = "Имя файла не может быть пустым")
    @Size(min = 3, max = 250, message = "Имя файла должно быть от 3 до 250 символов")
    @Schema(description = "Имя файла", example = "1.txt")
    @JsonProperty("fileName")
    var filename: String
)