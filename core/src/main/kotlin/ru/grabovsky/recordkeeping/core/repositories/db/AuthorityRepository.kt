package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.db.Authority
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
@Repository
interface AuthorityRepository: JpaRepository<Authority, Long> {
    fun findByName(name: String): Optional<Authority>
}