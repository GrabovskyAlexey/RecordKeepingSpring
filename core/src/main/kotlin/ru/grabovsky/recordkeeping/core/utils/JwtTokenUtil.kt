package ru.grabovsky.recordkeeping.core.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken.TokenType
import ru.grabovsky.recordkeeping.core.config.JwtProperties
import ru.grabovsky.recordkeeping.core.exceptions.IncorrectConfirmTokenException
import ru.grabovsky.recordkeeping.core.exceptions.ValidateTokenException
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.stream.Collectors
import javax.crypto.SecretKey

@Component
class JwtTokenUtil(
    private val properties: JwtProperties
) {
    private val log = getLogger(JwtTokenUtil::class.java)

    private final val accessKey: SecretKey = Keys.hmacShaKeyFor(properties.access.secret.toByteArray())
    private final val refreshKey: SecretKey = Keys.hmacShaKeyFor(properties.refresh.secret.toByteArray())
    private val accessParser = Jwts.parser().verifyWith(accessKey).build()
    private val refreshParser = Jwts.parser().verifyWith(refreshKey).build()

    fun getRoles(token: String) =
        getAllClaims(token, accessParser).get("roles", List::class.java).filterIsInstance<String>()

    fun getUsernameFromToken(token: String): String = getAllClaims(token, accessParser).subject

    private fun getAllClaims(token: String, parser: JwtParser): Claims {
        return try {
            parser.parseSignedClaims(token).payload
        } catch (e: SignatureException) {
            log.warn("Verify token error")
            throw ValidateTokenException("Verify token error")
        }
    }

    fun generateAccessToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, Any?> = HashMap()
        val rolesList: MutableList<String> = userDetails.authorities.stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.toList())
        claims["roles"] = rolesList
        val expiredDate = Date.from(
            LocalDateTime.now()
                .plusMinutes(properties.access.lifetime)
                .toInstant(ZoneOffset.UTC)
        )
        return Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuer(properties.issuer)
            .issuedAt(Date())
            .expiration(expiredDate)
            .add(claims)
            .and()
            .signWith(accessKey)
            .compact()
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        val refreshExpiration = Date.from(
            LocalDateTime.now()
                .plusDays(properties.refresh.lifetime)
                .toInstant(ZoneOffset.UTC)
        )
        return Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuer(properties.issuer)
            .issuedAt(Date())
            .expiration(refreshExpiration)
            .and()
            .signWith(accessKey)
            .compact()
    }

    fun generateConfirmToken(token: ConfirmToken): String {
        val claims: MutableMap<String, Any?> = HashMap()
        claims["confirm"] = token
        val expiredDate = Date.from(
            LocalDateTime.now()
                .plusHours(token.type?.lifetimeInHours ?: 0)
                .toInstant(ZoneOffset.UTC)
        )
        return Jwts.builder()
            .claims()
            .issuer(properties.issuer)
            .issuedAt(Date())
            .expiration(expiredDate)
            .add(claims)
            .and()
            .signWith(accessKey)
            .compact()
    }

    fun parseConfirmToken(token: String): ConfirmToken {
        val confirm: Map<String, String> = getAllClaims(token, accessParser).get("confirm", Map::class.java)
            .filterValues { it is String }
            .toMap() as Map<String, String>
        val confirmToken =
            ConfirmToken(
                confirm["email"],
                confirm["username"],
                confirm["code"],
                TokenType.getByName(confirm["type"]))
        if (confirmToken.isValidToken) {
            return confirmToken;
        }
        throw IncorrectConfirmTokenException("Ошибка в данных токена")
    }
}



