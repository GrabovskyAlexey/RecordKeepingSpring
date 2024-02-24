package ru.grabovsky.recordkeeping.api.dto.utils

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.grabovsky.recordkeeping.api.dto.interfaces.PageableDto
import jakarta.validation.constraints.Positive

/**
 * DTO для пагинации
 *
 * @author GrabovskyAlexey
 * @created 14.01.2024 12:55
 */
@Schema(description = "Страница для пагинации", name = "Page")
data class PageDto<T : PageableDto>(
    @Schema(description = "Элементы страницы")
    @JsonProperty("pageItems")
    val pageItems: MutableList<T> = mutableListOf(),

    @Schema(description = "Номер страницы")
    @JsonProperty("pageIndex")
    val pageIndex: @Positive Int,

    @Schema(description = "Первая страница?")
    @JsonProperty("isFirst")
    val isFirst: Boolean,

    @Schema(description = "Последняя страница?")
    @JsonProperty("isLast")
    val isLast: Boolean,

    @Schema(description = "Размер страницы")
    @JsonProperty("pageSize")
    val pageSize: Int,

    @Schema(description = "Всего страниц")
    @JsonProperty("totalPages")
    val totalPages: Int,

    @Schema(description = "Всего элементов")
    @JsonProperty("totalElements")
    val totalElements: Long
)
