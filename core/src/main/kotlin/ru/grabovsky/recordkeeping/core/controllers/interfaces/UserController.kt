package ru.grabovsky.recordkeeping.core.controllers.interfaces

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import ru.grabovsky.recordkeeping.api.dto.auth.AuthRequest
import ru.grabovsky.recordkeeping.api.dto.auth.AuthResponse
import ru.grabovsky.recordkeeping.api.dto.auth.RegisterRequest
import ru.grabovsky.recordkeeping.api.dto.utils.MessageDto

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 03.01.2024
 */

@Tag(name = "user", description = "Контроллер для работы с пользователями")
interface UserController {
    /**
     * POST /auth : create token
     *
     * @param authRequest authRequest Item (required)
     * @return Successfully create token (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
        operationId = "authenticate",
        summary = "Аутентификация",
        tags = ["user"],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Успешная аутентификация",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AuthResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = MessageDto::class)
                )]
            )
//            ,
//            ApiResponse(responseCode = "401", description = "Unauthorized", content = [Content()]),
//            ApiResponse(responseCode = "403", description = "Forbidden", content = [Content()])
        ]
    )
    @PostMapping(produces = ["application/json"], consumes = ["application/json"], path = ["/auth"])
    @ResponseStatus(
        HttpStatus.OK
    )
    fun auth(
        @Parameter(
            name = "Запрос аутентификации",
            description = "Запрос аутентификации",
            required = true
        ) @RequestBody authRequest: @Valid AuthRequest
    ): AuthResponse

    /**
     * POST /register : register user
     *
     * @param registerRequest registerRequest Item (required)
     * @return Successfully create token (status code 200)
     * or Bad Request (status code 400)
     */
    @Operation(
        operationId = "register",
        summary = "Регистрация",
        tags = ["user"],
        responses = [ApiResponse(
            responseCode = "201",
            description = "Токен авторизации при успешной регистрации",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = AuthResponse::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = MessageDto::class))]
        )]
    )
    @PostMapping(produces = ["application/json"], consumes = ["application/json"], path = ["/register"])
    @ResponseStatus(
        HttpStatus.CREATED
    )
    fun register(
        @Parameter(
            name = "Запрос регистрации",
            description = "Запрос регистрации",
            required = true
        ) @RequestBody registerRequest: @Valid RegisterRequest
    ): AuthResponse
}