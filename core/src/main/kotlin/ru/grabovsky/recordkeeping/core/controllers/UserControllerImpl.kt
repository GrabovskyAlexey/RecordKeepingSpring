package ru.grabovsky.recordkeeping.core.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.grabovsky.recordkeeping.api.dto.auth.AuthRequest
import ru.grabovsky.recordkeeping.api.dto.auth.AuthResponse
import ru.grabovsky.recordkeeping.api.dto.auth.RegisterRequest
import ru.grabovsky.recordkeeping.core.controllers.interfaces.UserController

@RestController
@RequestMapping
class UserControllerImpl : UserController {
    override fun auth(authRequest: AuthRequest): AuthResponse {
        TODO("Not yet implemented")
    }

    override fun register(registerRequest: RegisterRequest): AuthResponse {
        TODO("Not yet implemented")
    }
}