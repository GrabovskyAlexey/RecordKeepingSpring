package ru.grabovsky.recordkeeping.core.controllers.interfaces

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationInfoResponseDto
import ru.grabovsky.recordkeeping.api.dto.organization.OrganizationShortInfoDto
import ru.grabovsky.recordkeeping.api.dto.utils.MessageDto
import java.security.Principal

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 14.01.2024
 */

@Validated
@Tag(name = "organization", description = "Контроллер для работы с организациями")
@SecurityRequirement(name = "JWTAuth")
interface OrganizationController {

    /**
     * GET ${application.api.url}/organization : Get all organization (Admin only)
     *
     * @return Response contains list of all organizations (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "getAllOrganization",
        summary = "Получить список всех организаций (Доступно только Администратору приложения)",
        tags = ["organization"],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Список всех организаций",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = OrganizationInfoResponseDto::class)
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = MessageDto::class)
                )]
            ),
            ApiResponse(
                responseCode = "401",
                description = "Unauthorized"
            ),
            ApiResponse(
                responseCode = "403",
                description = "Forbidden"
            )
        ]
    )
    @GetMapping(
        produces = ["application/json"]
    )
    @ResponseStatus(
        HttpStatus.OK
    )
    fun getAllOrganization(principal: Principal): OrganizationInfoResponseDto


    /**
     * GET ${application.api.url}/organization/{id} : Get organization by id
     *
     * @return organization (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "getOrganizationById",
        summary = "Получить организацию по id",
        tags = ["organization"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Организация",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = OrganizationDto::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Организация не найдена",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
        )]
    )
    @GetMapping(value = ["/{id}"], produces = ["application/json"])
    fun getOrganizationById(
        @Parameter(name = "id", description = "organization id", required = true)
        @PathVariable("id") id: Long,
        principal: Principal
    ): OrganizationDto

    /**
     * POST ${application.api.url}/organization/ : Add organization
     *
     * @param organization organization info (required)
     * @return Successfully create organization (status code 201)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "createOrganization",
        summary = "Создание организации",
        tags = ["organization"],
        responses = [ApiResponse(
            responseCode = "201",
            description = "Успешно созданная организация",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = OrganizationShortInfoDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
        ), ApiResponse(
            responseCode = "403",
            description = "Forbidden"
        )]
    )
    @PostMapping(produces = ["application/json"], consumes = ["application/json"])
    @ResponseStatus(
        HttpStatus.CREATED
    )
    fun createOrganization(
        @Parameter(
            name = "Организация",
            description = "Данные организации",
            required = true
        ) @RequestBody organization: @Valid OrganizationShortInfoDto,
        principal: Principal
    ): OrganizationShortInfoDto

    /**
     * PUT ${application.api.url}/organization/{id} : Update organization by id
     *
     * @param organization organization info (required)
     * @return Successfully update organization (status code 204)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found task (status code 404)
     */
    @Operation(
        operationId = "updateOrganization",
        summary = "Обновить организацию",
        tags = ["organization"],
        responses = [ApiResponse(responseCode = "204", description = "Организация успешно обновлена"),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
            ), ApiResponse(
                responseCode = "401",
                description = "Unauthorized"
            ), ApiResponse(
                responseCode = "403",
                description = "Forbidden"
            ), ApiResponse(
                responseCode = "404",
                description = "Организация не найдена",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
            )]
    )
    @PutMapping(produces = ["application/json"], consumes = ["application/json"])
    @ResponseStatus(
        HttpStatus.NO_CONTENT
    )
    fun updateOrganization(
        @Parameter(name = "id", description = "organization id", required = true) @PathVariable("id") id: Long,
        @Parameter(
            name = "organization",
            description = "organization info",
            required = true
        ) @RequestBody organization: @Valid OrganizationShortInfoDto,
        principal: Principal
    )

    /**
     * DELETE ${application.api.url}/organization/{id} : Delete organization by id
     *
     * @param id organization id (required)
     * @return Successfully delete organization (status code 204)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found task (status code 404)
     */
    @Operation(
        operationId = "deleteOrganization",
        summary = "Удаление организации",
        tags = ["organization"],
        responses = [
            ApiResponse(
                responseCode = "204",
                description = "Организация успешно удалена"
            ), ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
            ), ApiResponse(
                responseCode = "401",
                description = "Unauthorized"
            ), ApiResponse(
                responseCode = "403",
                description = "Forbidden"
            ), ApiResponse(
                responseCode = "404",
                description = "Организация не найдена",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
            )]
    )
    @DeleteMapping(value = ["/{id}"], produces = ["application/json"])
    @ResponseStatus(
        HttpStatus.NO_CONTENT
    )
    fun deleteOrganization(
        @Parameter(
            name = "id",
            description = "organization id",
            required = true)
        @PathVariable("id") id: Long,
        principal: Principal
    )
}