package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate

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
    var id: Long? = null,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description", nullable = false)
    var description: String
) {

    @ManyToMany
    @JoinTable(
        name = "roles_authorities",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id")]
    )
    val authorities: MutableSet<Authority> = mutableSetOf();

    @ManyToMany(mappedBy = "roles")
    val users: Set<User> = setOf();

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        val role = other as Role
        return id != null && id == role.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Role(id=$id, role='$name', description='$description')"
    }
}