package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
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

    @Column(name = "enabled", nullable = false)
    var isEnabled: Boolean = false

    @ElementCollection
    @CollectionTable(name = "companies_users_roles", joinColumns = [JoinColumn(name = "company_id")])
    val companyUserRole: MutableSet<CompanyUserRole> = mutableSetOf();

    override fun toString(): String {
        return "Company(id=$id, name='$name', enabled=$isEnabled)"
    }
}