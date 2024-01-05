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
data class UserAction (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        val userAction = other as UserAction
        return id != null && id == userAction.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "UserLog(id=$id, action='$action', description='$description', user=$user)"
    }
}