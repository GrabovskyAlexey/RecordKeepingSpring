package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for user account table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {
    @Column(name = "username", nullable = false)
    lateinit var username: String

    @Column(name = "password", nullable = false)
    lateinit var password: String

    @Column(name = "email", nullable = false)
    lateinit var email: String

    @Column(name = "enabled", nullable = false)
    var isEnabled: Boolean = false

    @Column(name = "activated", nullable = false)
    var isActivated: Boolean = false

    @Column(name = "activation_code")
    lateinit var activationCode: String

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @OneToOne(mappedBy = "user", cascade = [CascadeType.MERGE, CascadeType.PERSIST], orphanRemoval = true)
    lateinit var userInfo: UserInfo

    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: MutableSet<Role> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "users_companies",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "company_id")]
    )
    val companies: MutableSet<Company> = mutableSetOf()

    @OneToMany(mappedBy = "author")
    val records: Set<Record> = setOf()

    @OneToMany(mappedBy = "user")
    val userActions: Set<UserAction> = setOf()

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', isEnabled=$isEnabled, isActivated=$isActivated, userInfo=$userInfo)"
    }
}