package ru.grabovsky.recordkeeping.core.repositories.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.grabovsky.recordkeeping.core.entity.db.CompanyUserRole
import ru.grabovsky.recordkeeping.core.entity.db.CompanyUserRolePK
import java.util.Optional

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 28.01.2024 12:13
 */
@Repository
interface CompanyUserRoleRepository: JpaRepository<CompanyUserRole, CompanyUserRolePK> {
    @Query("""
        SELECT cr FROM CompanyUserRole cr
        WHERE cr.role.user.id = :userId AND cr.role.company.id = :companyId
    """)
    fun findByUserIdAndCompanyId(userId: Long, companyId: Long): Optional<CompanyUserRole>

    @Query("""
        SELECT cr FROM CompanyUserRole cr
        WHERE cr.role.user.id = :userId
    """)
    fun findAllByUserId(userId: Long): MutableSet<CompanyUserRole>

    @Query("""
        SELECT cr FROM CompanyUserRole cr
        WHERE cr.role.company.id = :companyId
    """)
    fun findAllByCompanyId(companyId: Long): MutableSet<CompanyUserRole>
}