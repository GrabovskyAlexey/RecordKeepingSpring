package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.types.AuthorityTypes
import ru.grabovsky.recordkeeping.core.entity.db.Authority
import ru.grabovsky.recordkeeping.core.repositories.db.AuthorityRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.AuthorityService

@Service
class AuthorityServiceImpl(
    private val repository: AuthorityRepository
) : AuthorityService {
    override fun getDefaultAuthority(): Authority {
        return findByType(AuthorityTypes.EDIT_PROFILE).orElseGet { createDefaultAuthority() }
    }

    override fun findByType(type: AuthorityTypes) = repository.findByType(type)
    override fun save(authority: Authority) = repository.save(authority)

    private fun createDefaultAuthority(): Authority {
        val authority = Authority(
            type = AuthorityTypes.EDIT_PROFILE, description = "Редактирование профиля"
        )
        return save(authority)
    }
}