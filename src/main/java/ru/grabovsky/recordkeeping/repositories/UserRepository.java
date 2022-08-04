package ru.grabovsky.recordkeeping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.recordkeeping.models.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsUserByUsernameIgnoreCase(String username);
    boolean existsUserByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);
}
