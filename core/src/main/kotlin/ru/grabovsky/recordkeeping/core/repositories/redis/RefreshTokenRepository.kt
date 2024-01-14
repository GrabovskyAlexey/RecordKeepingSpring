package ru.grabovsky.recordkeeping.core.repositories.redis

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.redis.RefreshToken

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String>
