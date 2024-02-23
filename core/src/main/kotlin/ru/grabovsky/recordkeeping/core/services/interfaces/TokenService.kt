package ru.grabovsky.recordkeeping.core.services.interfaces

import org.springframework.security.core.userdetails.UserDetails
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 17.02.2024 17:31
 */

interface TokenService {
    fun getAccessToken(userDetails: UserDetails): String
    fun getRefreshToken(userDetails: UserDetails): String
    fun getConfirmToken(token: String): ConfirmToken

}