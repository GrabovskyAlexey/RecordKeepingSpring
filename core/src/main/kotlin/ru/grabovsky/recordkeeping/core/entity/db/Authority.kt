package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import ru.grabovsky.recordkeeping.api.types.AuthorityTypes

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
    @Column(name = "description", nullable = false)
    var description: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: AuthorityTypes
): BaseEntity(id) {

    @ManyToMany(mappedBy = "authorities")
    val roles: MutableSet<Role> = mutableSetOf()


    override fun toString(): String {
        return "Authority(id=$id, authority='$type', description='$description')"
    }

}