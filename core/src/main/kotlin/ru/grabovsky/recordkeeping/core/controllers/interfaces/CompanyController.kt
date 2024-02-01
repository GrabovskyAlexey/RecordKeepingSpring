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
import ru.grabovsky.recordkeeping.api.dto.company.CompanyDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyInfoResponseDto
import ru.grabovsky.recordkeeping.api.dto.company.CompanyShortInfoDto
import ru.grabovsky.recordkeeping.api.dto.utils.MessageDto
import java.security.Principal

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 14.01.2024
 */

@Validated
@Tag(name = "company", description = "Контроллер для работы с организациями")
@SecurityRequirement(name = "JWTAuth")
interface CompanyController {

    /**
     * GET ${application.api.url}/company : Get all company (Admin only)
     *
     * @return Response contains list of all companies (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "getAllCompany",
        summary = "Получить список всех компаний (Доступно только Администратору приложения)",
        tags = ["company"],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Список всех компаний",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CompanyInfoResponseDto::class)
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
    fun getAllCompany(principal: Principal): CompanyInfoResponseDto


    /**
     * GET ${application.api.url}/company/{id} : Get company by id
     *
     * @return Company (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "getCompanyById",
        summary = "Получить организацию по id",
        tags = ["company"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Организация",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CompanyDto::class))]
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
    fun getCompanyById(
        @Parameter(name = "id", description = "Company id", required = true)
        @PathVariable("id") id: Long,
        principal: Principal
    ): CompanyDto

    /**
     * POST ${application.api.url}/company/ : Add company
     *
     * @param company Company info (required)
     * @return Successfully create company (status code 201)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "createCompany",
        summary = "Создание организации",
        tags = ["company"],
        responses = [ApiResponse(
            responseCode = "201",
            description = "Успешно созданная организация",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = CompanyShortInfoDto::class)
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
    fun createCompany(
        @Parameter(
            name = "Организация",
            description = "Данные организации",
            required = true
        ) @RequestBody company: @Valid CompanyShortInfoDto,
        principal: Principal
    ): CompanyShortInfoDto

    /**
     * PUT ${application.api.url}/company/{id} : Update company by id
     *
     * @param company Company info (required)
     * @return Successfully update company (status code 204)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found task (status code 404)
     */
    @Operation(
        operationId = "updateCompany",
        summary = "Обновить организацию",
        tags = ["company"],
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
    fun updateCompany(
        @Parameter(name = "id", description = "Company id", required = true) @PathVariable("id") id: Long,
        @Parameter(
            name = "Company",
            description = "Company info",
            required = true
        ) @RequestBody company: @Valid CompanyShortInfoDto,
        principal: Principal
    )

    /**
     * DELETE ${application.api.url}/company/{id} : Delete company by id
     *
     * @param id company id (required)
     * @return Successfully delete company (status code 204)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found task (status code 404)
     */
    @Operation(
        operationId = "deleteCompany",
        summary = "Удаление организации",
        tags = ["company"],
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
    fun deleteCompany(
        @Parameter(
            name = "id",
            description = "company id",
            required = true)
        @PathVariable("id") id: Long,
        principal: Principal
    )
}