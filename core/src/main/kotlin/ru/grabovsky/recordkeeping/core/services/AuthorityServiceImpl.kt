package ru.grabovsky.recordkeeping.core.services

import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.core.entity.db.Authority
import ru.grabovsky.recordkeeping.core.repositories.db.AuthorityRepository
import ru.grabovsky.recordkeeping.core.services.interfaces.AuthorityService

@Service
class AuthorityServiceImpl(
    private val repository: AuthorityRepository
) : AuthorityService {
    override fun getDefaultAuthority(): Authority {
        return findByName("editProfile").orElseGet { createDefaultAuthority() }
    }

    override fun findByName(name: String) = repository.findByName(name)

    override fun save(authority: Authority) = repository.save(authority)

    private fun createDefaultAuthority(): Authority {
        val authority = Authority(
            name = "editProfile", description = "Редактирование профиля"
        )
        return save(authority)
    }
}