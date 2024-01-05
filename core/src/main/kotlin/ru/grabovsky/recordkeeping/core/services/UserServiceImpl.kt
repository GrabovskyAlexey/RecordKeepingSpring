package ru.grabovsky.recordkeeping.core.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.dto.auth.AuthRequest
import ru.grabovsky.recordkeeping.api.dto.auth.AuthResponse
import ru.grabovsky.recordkeeping.api.dto.auth.RegisterRequest
import ru.grabovsky.recordkeeping.api.notification.SimpleTextEmailMessage
import ru.grabovsky.recordkeeping.core.entity.db.Role
import ru.grabovsky.recordkeeping.core.entity.db.User
import ru.grabovsky.recordkeeping.core.entity.redis.RefreshToken
import ru.grabovsky.recordkeeping.core.exceptions.EmailAlreadyExistsException
import ru.grabovsky.recordkeeping.core.exceptions.UserAlreadyExistsException
import ru.grabovsky.recordkeeping.core.repositories.db.UserRepository
import ru.grabovsky.recordkeeping.core.repositories.redis.RefreshTokenRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.RoleService
import ru.grabovsky.recordkeeping.core.services.interfaces.UserService
import ru.grabovsky.recordkeeping.core.utils.JwtTokenUtil
import java.util.*

@Service
class UserServiceImpl(
    @Lazy private val authenticationManager: AuthenticationManager,
    @Value("\${application.invite.url}") private val url: String,
    @Value("\${application.invite.email-address-from}") private val emailFrom: String,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil,
    private val kafkaTemplate: KafkaTemplate<String, SimpleTextEmailMessage>,
    private val userRepository: UserRepository,
    private val refreshRepository: RefreshTokenRepository,
    private val roleService: RoleService,
//    private val passwordResetTokenRepository: PasswordResetTokenRepository? = null,
) : UserService {
    override fun authenticate(request: AuthRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )
        return createAuthResponseByUsername(request.username)
    }

    override fun register(request: RegisterRequest): AuthResponse {
        createNewUser(request)
        return createAuthResponseByUsername(request.username)

    }

    private fun createAuthResponseByUsername(username: String): AuthResponse {
        val userDetails = loadUserByUsername(username)

        val accessToken = jwtTokenUtil.generateAccessToken(userDetails)
        val refreshToken = jwtTokenUtil.generateRefreshToken(userDetails)
        val refresh = RefreshToken(userDetails.username, refreshToken)
        refreshRepository.save(refresh)
        val authResponse = AuthResponse(accessToken, refreshToken)
        return authResponse
    }

    private fun createNewUser(request: RegisterRequest) {
        when {
            userRepository.existsByUsernameIgnoreCase(request.username) ->
                throw UserAlreadyExistsException("Пользователь с логином ${request.username} уже существует")

            userRepository.existsByEmailIgnoreCase(request.email) ->
                throw EmailAlreadyExistsException("Пользователь с электронной почтой ${request.email} уже существует")
        }
        val uuid = UUID.randomUUID().toString()
        val roles: Set<Role> = setOf(roleService.getDefaultRole())
        val user = User()
        user.username = request.username
        user.password = bCryptPasswordEncoder.encode(request.password)
        user.email = request.email
        user.isActivated = false
        user.isEnabled = true
        user.activationCode = uuid
        user.roles.addAll(roles)
        userRepository.save(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username).orElseThrow {
            throw UsernameNotFoundException("User '$username' not found")
        }
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            getUserRoles(user.roles)
        )
    }

    private fun getUserRoles(roles: Collection<Role>): Collection<GrantedAuthority> {
        if (roles.isEmpty()) {
            return setOf()
        }
        return getRolesAndPrivileges(roles)
            .map { SimpleGrantedAuthority(it) }
            .toSet()
    }

    //TODO Подумать над более оптимальным алгоритмом
    private fun getRolesAndPrivileges(roles: Collection<Role>): Collection<String> {
        val result: MutableSet<String> = HashSet()
        for (role in roles) {
            result.add(role.name)
            result.addAll(
                role.authorities
                    .map { it.name }
                    .toSet()
            )
        }
        return result
    }
}