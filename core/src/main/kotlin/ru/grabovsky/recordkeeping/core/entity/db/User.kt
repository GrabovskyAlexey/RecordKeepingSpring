package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
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
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
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
    var activationCode: String? = null

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @OneToOne(mappedBy = "user")
    lateinit var userInfo: UserInfo

    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: MutableSet<Role> = mutableSetOf()

    @ManyToOne
    @JoinColumn(name = "company_id")
    lateinit var company: Company;

    @OneToMany(mappedBy = "author")
    val records: Set<Record> = setOf();

    @OneToMany(mappedBy = "user")
    val userActions: Set<UserAction> = setOf();

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        val user = other as User
        return id != null && id == user.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', isEnabled=$isEnabled, isActivated=$isActivated, userInfo=$userInfo, company=$company)"
    }
}