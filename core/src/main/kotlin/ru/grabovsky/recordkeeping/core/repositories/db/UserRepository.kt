package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.db.User
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 03.01.2024
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String) : Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun existsByEmailIgnoreCase(email: String): Boolean
    fun existsByUsernameIgnoreCase(username: String): Boolean
}