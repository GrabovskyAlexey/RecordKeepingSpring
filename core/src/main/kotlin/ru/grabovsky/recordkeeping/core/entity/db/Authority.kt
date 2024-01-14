package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * Entity for authorities table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "authorities")
class Authority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description", nullable = false)
    var description: String
): BaseEntity(id) {

    @ManyToMany(mappedBy = "authorities")
    val roles: MutableSet<Role> = mutableSetOf()

    override fun toString(): String {
        return "Authority(id=$id, authority='$name', description='$description')"
    }

}