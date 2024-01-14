package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for company project table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "projects")
class Project (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {

    @Column(name = "short_name", nullable = false)
    lateinit var shortName: String

    @Column(name = "full_name")
    var fullName: String? = null

    @Column(name = "description")
    var description: String? = null

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    lateinit var company: Company

    @OneToMany(mappedBy = "project")
    val records: List<Record> = listOf()

    override fun toString(): String {
        return "Project(id=$id, shortName='$shortName', fullName=$fullName, description=$description)"
    }
}