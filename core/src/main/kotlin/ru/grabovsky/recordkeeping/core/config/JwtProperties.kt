package ru.grabovsky.recordkeeping.core.config

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 13.01.2024
 */

@ConfigurationProperties("application.security.jwt")
data class JwtProperties(
    val issuer: String,
    val access: InnerJwtProperties,
    val refresh: InnerJwtProperties
) {
    data class InnerJwtProperties(val secret: String, val lifetime: Long)
}

