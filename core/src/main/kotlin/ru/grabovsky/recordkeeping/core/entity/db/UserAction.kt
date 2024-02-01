package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

/**
 * Entity for logging user action
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "user_actions")
class UserAction (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {

    @Column(name = "action")
    lateinit var action: String

    @Column(name = "description")
    lateinit var description: String

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    lateinit var user: User

    override fun toString(): String {
        return "UserLog(id=$id, action='$action', description='$description', user=$user)"
    }
}