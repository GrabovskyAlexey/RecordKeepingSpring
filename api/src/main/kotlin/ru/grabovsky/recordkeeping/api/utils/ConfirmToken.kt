package ru.grabovsky.recordkeeping.api.utils

import java.util.*

data class ConfirmToken(
    private val email: String,
    private val code: String,
    private val type: TokenType
) {
    enum class TokenType {
        REGISTER, INVITE, RESET_PASSWORD;

        companion object {
            fun getByName(name: String?): TokenType? {
                return if (Objects.isNull(name)) {
                    null
                } else valueOf(name!!)
            }
        }
    }

    val isValidToken: Boolean
        get() = Objects.nonNull(email) && Objects.nonNull(code) && Objects.nonNull(type)
}