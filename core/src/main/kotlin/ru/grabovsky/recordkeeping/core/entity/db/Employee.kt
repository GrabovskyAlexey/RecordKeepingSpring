package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for organization employees table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "employees")
class Employee(
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

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    lateinit var organization: Organization

    @OneToMany(mappedBy = "employee")
    val records: List<Record> = listOf()

    override fun toString(): String {
        return "Employee(id=$id, name='$name', organization=$organization)"
    }


}