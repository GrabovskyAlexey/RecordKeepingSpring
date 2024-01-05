package ru.grabovsky.recordkeeping.core.services.interfaces

import org.springframework.security.core.userdetails.UserDetailsService
import ru.grabovsky.recordkeeping.api.dto.auth.AuthRequest
import ru.grabovsky.recordkeeping.api.dto.auth.AuthResponse
import ru.grabovsky.recordkeeping.api.dto.auth.RegisterRequest

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 03.01.2024
 */
interface UserService : UserDetailsService {
    fun authenticate(request: AuthRequest): AuthResponse

    fun register(request: RegisterRequest) : AuthResponse
}