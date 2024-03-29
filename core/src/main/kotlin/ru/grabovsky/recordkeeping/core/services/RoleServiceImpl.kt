package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.types.ApplicationRoleTypes
import ru.grabovsky.recordkeeping.core.entity.db.Role
import ru.grabovsky.recordkeeping.core.repositories.db.RoleRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.AuthorityService
import ru.grabovsky.recordkeeping.core.services.interfaces.RoleService

@Service
class RoleServiceImpl(
    private val repository: RoleRepository,
    private val authorityService: AuthorityService
) : RoleService {
    override fun findByName(name: ApplicationRoleTypes) = repository.findByName(name)
    override fun save(role: Role): Role = repository.save(role)

    override fun getDefaultRole(): Role {
        return findByName(ApplicationRoleTypes.ROLE_UNACTIVATED_USER).orElseGet { createDefaultRole() }
    }

    private fun createDefaultRole(): Role {
        val role = Role(
            name = ApplicationRoleTypes.ROLE_UNACTIVATED_USER,
            description = "Пользователь не подтвердивший адрес электронной почты"
        )
        role.authorities.add(authorityService.getDefaultAuthority())
        save(role)
        return role
    }
}