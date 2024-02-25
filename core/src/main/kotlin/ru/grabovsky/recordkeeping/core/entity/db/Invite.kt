package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for organization invite table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "invites")
class Invite (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {

    @Column(name = "email", nullable = false)
    lateinit var email: String

    @Column(name = "invite_code", nullable = false)
    lateinit var inviteCode: String

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    lateinit var organization: Organization

    override fun toString(): String {
        return "Invite(id=$id, email='$email', inviteCode='$inviteCode', organization=$organization)"
    }
}