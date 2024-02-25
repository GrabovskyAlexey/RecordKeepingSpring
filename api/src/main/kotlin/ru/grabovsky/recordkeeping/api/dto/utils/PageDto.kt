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
@Schema(description = "swagger.page.info", name = "Page")
data class PageDto<T : PageableDto>(
    @Schema(description = "swagger.page.items")
    @JsonProperty("pageItems")
    val pageItems: MutableList<T> = mutableListOf(),

    @Schema(description = "swagger.page.index")
    @JsonProperty("pageIndex")
    val pageIndex: @Positive Int,

    @Schema(description = "swagger.page.isFirst")
    @JsonProperty("isFirst")
    val isFirst: Boolean,

    @Schema(description = "swagger.page.isLast")
    @JsonProperty("isLast")
    val isLast: Boolean,

    @Schema(description = "swagger.page.size")
    @JsonProperty("pageSize")
    val pageSize: Int,

    @Schema(description = "swagger.page.total")
    @JsonProperty("totalPages")
    val totalPages: Int,

    @Schema(description = "swagger.page.items.total")
    @JsonProperty("totalItems")
    val totalItems: Long
)
