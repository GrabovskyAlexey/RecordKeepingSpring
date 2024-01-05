package ru.grabovsky.recordkeeping.core.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.JWTVerifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken
import ru.grabovsky.recordkeeping.core.exceptions.IncorrectConfirmTokenException
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.stream.Collectors

@Component
class JwtTokenUtil(
    @Value("\${application.security.jwt.access.secret}") private val accessSecret: String,
    @Value("\${application.security.jwt.refresh.secret}") private val refreshSecret: String,
    @Value("\${application.security.jwt.issuer}") private val issuer: String,
    @Value("\${application.security.jwt.access.lifetime}") private val accessExpiration: Long,
    @Value("\${application.security.jwt.refresh.lifetime}") private val refreshExpiration: Long
) {
    private val log = getLogger(JwtTokenUtil::class.java)

    val algorithmAccess: Algorithm = Algorithm.HMAC512(accessSecret);
    val algorithmRefresh: Algorithm = Algorithm.HMAC512(refreshSecret);

    fun verifyToken(token: String, algorithm: Algorithm): Boolean {
        return try {
            val verifier: JWTVerifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .acceptLeeway(1)   //1 sec for nbf and iat
                .acceptExpiresAt(5)
                .build()
            verifier.verify(token);
            true;
        } catch (e: JWTVerificationException) {
            log.warn("Verify token error")
            false;
        }
    }

    fun getUsernameFromToken(token: String): String {
        return JWT.decode(token).subject;
    }

    private fun testGenerate() {
//        JWT.create().
    }

    fun getRoles(token: String): MutableList<String> {
        return JWT.decode(token).getClaim("roles").asList(String::class.java);
    }

    fun generateAccessToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, Any?> = HashMap()
        val rolesList: MutableList<String> = userDetails.authorities.stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.toList())
        claims["roles"] = rolesList
        val issuedDate = Date()
        val refresh = LocalDateTime.now().plusMinutes(accessExpiration).toInstant(ZoneOffset.UTC)
        val expiredDate = Date.from(refresh)
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(userDetails.username)
            .withIssuedAt(issuedDate)
            .withExpiresAt(expiredDate)
            .withPayload(claims)
            .sign(algorithmAccess)
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        val issuedDate = Date()
        val refresh = LocalDateTime.now().plusDays(refreshExpiration).toInstant(ZoneOffset.UTC)
        val refreshExpiration = Date.from(refresh)
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(userDetails.username)
            .withIssuedAt(issuedDate)
            .withExpiresAt(refreshExpiration)
            .sign(algorithmRefresh)
    }


    fun generateConfirmationToken(token: ConfirmToken): String {
        val claims: MutableMap<String, Any?> = HashMap()
        claims["confirm"] = token
        val calendar = Calendar.getInstance()
        val issuedDate = calendar.time
        calendar.add(Calendar.DAY_OF_MONTH, 7)
        val expiredDate = calendar.time
        return JWT.create()
            .withIssuer(issuer)
            .withIssuedAt(issuedDate)
            .withExpiresAt(expiredDate)
            .withPayload(claims)
            .sign(algorithmAccess)
    }

    fun parseConfirmToken(token: String): ConfirmToken {
        if (verifyToken(token, algorithmAccess)) {
            val confirmToken = JWT.decode(token).getClaim("confirm").`as`(ConfirmToken::class.java);
            if (confirmToken.isValidToken) {
                return confirmToken;
            }
        }
        throw IncorrectConfirmTokenException("Ошибка в данных токена")
    }
}



