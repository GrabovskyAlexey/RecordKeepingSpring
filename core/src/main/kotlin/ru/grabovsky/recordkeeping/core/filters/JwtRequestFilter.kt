package ru.grabovsky.recordkeeping.core.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.grabovsky.recordkeeping.core.utils.JwtTokenUtil
import ru.grabovsky.recordkeeping.core.utils.getLogger
import java.io.IOException
import java.util.stream.Collectors

/**
 * Фильтр для обработки запросов с JWT токенами
 *
 * @author GrabovskyAlexey
 */
@Component
class JwtRequestFilter(private val jwtTokenUtil: JwtTokenUtil) : OncePerRequestFilter() {
    private val log = getLogger(javaClass);

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")
        var username: String? = null
        var jwt: String = ""
        if (authHeader != null) {
            if (authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7)
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwt)
                } catch (e: Exception) {
                    log.debug("The token is expired")
                }
            }
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val token = UsernamePasswordAuthenticationToken(
                username,
                null, jwtTokenUtil.getRoles(jwt).stream().map { role: String -> SimpleGrantedAuthority(role) }.collect(
                    Collectors.toList()
                )
            )
            SecurityContextHolder.getContext().authentication = token
        }
        filterChain.doFilter(request, response)
    }
}
