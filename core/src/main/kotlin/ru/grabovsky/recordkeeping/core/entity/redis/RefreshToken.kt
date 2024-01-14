package ru.grabovsky.recordkeeping.core.entity.redis

import org.springframework.data.redis.core.RedisHash


@RedisHash("RefreshToken")
data class RefreshToken(
    val id: String,
    val token: String
) {

}
