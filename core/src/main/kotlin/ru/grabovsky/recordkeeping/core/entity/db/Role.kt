package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import ru.grabovsky.recordkeeping.api.types.ApplicationRoleTypes

/**
 * Entity for roles table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "roles")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    var name: ApplicationRoleTypes,
    @Column(name = "description", nullable = false)
    var description: String
): BaseEntity(id){

    @ManyToMany
    @JoinTable(
        name = "roles_authorities",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
    val authorities: MutableSet<Authority> = mutableSetOf()

    @ManyToMany(mappedBy = "roles")
    val users: Set<User> = setOf()

    override fun toString(): String {
        return "Role(id=$id, role='$name', description='$description')"
    }
}