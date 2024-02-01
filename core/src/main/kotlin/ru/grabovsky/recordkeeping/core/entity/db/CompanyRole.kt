package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import ru.grabovsky.recordkeeping.api.types.CompanyRoleTypes

/**
 * Entity for roles table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "company_roles")
class CompanyRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description", nullable = false)
    var description: String
): BaseEntity(id){

    @ManyToMany
    @JoinTable(
        name = "company_roles_authorities",
        joinColumns = [JoinColumn(name = "company_role_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
    val authorities: MutableSet<Authority> = mutableSetOf()

    override fun toString(): String {
        return "Role(id=$id, role='$name', description='$description')"
    }
}