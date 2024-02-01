package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.db.UserInfo

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 08.01.2024
 */
@Repository
interface UserInfoRepository: JpaRepository<UserInfo, Long> {
}