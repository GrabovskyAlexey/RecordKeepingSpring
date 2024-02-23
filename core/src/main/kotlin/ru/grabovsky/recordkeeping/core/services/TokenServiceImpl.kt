package ru.grabovsky.recordkeeping.core.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.core.entity.redis.RefreshToken
import ru.grabovsky.recordkeeping.core.repositories.redis.RefreshTokenRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.TokenService
import ru.grabovsky.recordkeeping.core.utils.JwtTokenUtil

@Service
class TokenServiceImpl(
    private val jwtTokenUtil: JwtTokenUtil,
    private val refreshRepository: RefreshTokenRepository,
) : TokenService {
    override fun getAccessToken(userDetails: UserDetails): String = jwtTokenUtil.generateAccessToken(userDetails)

    override fun getRefreshToken(userDetails: UserDetails): String {
        val refreshToken = jwtTokenUtil.generateRefreshToken(userDetails)
        val refresh = RefreshToken(userDetails.username, refreshToken)
        refreshRepository.save(refresh)
        return refreshToken
    }

    override fun getConfirmToken(token: String) = jwtTokenUtil.parseConfirmToken(token)
}