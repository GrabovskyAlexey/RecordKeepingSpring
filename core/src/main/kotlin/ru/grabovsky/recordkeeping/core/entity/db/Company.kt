package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for company info table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "companies")
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {


    @Column(name = "name", nullable = false)
    lateinit var name: String

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @OneToMany(mappedBy = "company")
    val contractors: List<Contractor> = listOf()

    @OneToMany(mappedBy = "company")
    val employees: List<Employee> = listOf()

    @OneToMany(mappedBy = "company")
    val invites: List<Invite> = listOf()

    @OneToMany(mappedBy = "company")
    val projects: List<Project> = listOf()

    @OneToMany(mappedBy = "company")
    val records: List<Record> = listOf()

    @OneToMany(mappedBy = "company")
    val users: List<User> = listOf()

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        val company = other as Company
        return id != null && id == company.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Company(id=$id, name='$name', enabled=$enabled)"
    }
}