package ru.grabovsky.recordkeeping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.recordkeeping.models.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
