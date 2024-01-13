package ru.grabovsky.recordkeeping.api.utils

data class ConfirmToken(
    val email: String?,
    val username: String?,
    val code: String?,
    val type: TokenType?
) {
    enum class TokenType(val lifetimeInHours: Long) {
        EMAIL_CONFIRM(72),
        INVITE(168),
        RESET_PASSWORD(24),
        INCORRECT(-1);

        companion object {
            fun getByName(name: String?) = if (name != null) valueOf(name) else INCORRECT
        }
    }

    val isValidToken: Boolean
        get() = when (type) {
            TokenType.EMAIL_CONFIRM, TokenType.RESET_PASSWORD -> email != null && username != null && code != null
            TokenType.INVITE -> email != null && code != null
            else -> false
        }
}