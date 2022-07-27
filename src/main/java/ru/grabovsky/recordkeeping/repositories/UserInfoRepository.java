package ru.grabovsky.recordkeeping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.recordkeeping.models.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
