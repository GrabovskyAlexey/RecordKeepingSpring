package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for organization info table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "organizations")
class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {
    @Column(name = "name", nullable = false)
    lateinit var name: String

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @OneToMany(mappedBy = "organization")
    val contractors: List<Contractor> = listOf()

    @OneToMany(mappedBy = "organization")
    val employees: List<Employee> = listOf()

    @OneToMany(mappedBy = "organization")
    val invites: List<Invite> = listOf()

    @OneToMany(mappedBy = "organization")
    val projects: List<Project> = listOf()

    @OneToMany(mappedBy = "organization")
    val records: List<Record> = listOf()

    @Column(name = "enabled", nullable = false)
    var isEnabled: Boolean = false

    @ManyToMany
    @JoinTable(
        name = "users_organizations",
        inverseJoinColumns = [JoinColumn(name = "user_id")],
        joinColumns = [JoinColumn(name = "organization_id")]
    )
    val users: MutableSet<User> = mutableSetOf()

    override fun toString(): String {
        return "Organization(id=$id, name='$name', enabled=$isEnabled)"
    }
}