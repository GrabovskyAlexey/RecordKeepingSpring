package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import ru.grabovsky.recordkeeping.core.entity.db.UserInfo

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 08.01.2024
 */
interface UserInfoRepository: JpaRepository<UserInfo, Long> {
}