package ru.grabovsky.recordkeeping.core.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grabovsky.recordkeeping.api.dto.auth.AuthRequest
import ru.grabovsky.recordkeeping.api.dto.auth.AuthResponse
import ru.grabovsky.recordkeeping.api.dto.auth.RegisterRequest
import ru.grabovsky.recordkeeping.api.dto.utils.TokenDto
import ru.grabovsky.recordkeeping.core.controllers.interfaces.UserController
import ru.grabovsky.recordkeeping.core.services.interfaces.UserService

@RestController
@RequestMapping
class UserControllerImpl(
    private val userService: UserService
) : UserController {
    override fun auth(request: AuthRequest): AuthResponse {
        return userService.authenticate(request)
    }

    override fun register(request: RegisterRequest): AuthResponse {
        return userService.register(request)
    }

    override fun confirmEmail(token: TokenDto): AuthResponse {
        return userService.confirmEmail(token)
    }
}