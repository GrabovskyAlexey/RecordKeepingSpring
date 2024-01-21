package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for contractors table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "contractors")
class Contractor(
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
    @JoinColumn(name = "company_id", nullable = false)
    lateinit var company: Company

    @OneToMany(mappedBy = "contractor")
    val records: List<Record> = listOf()

    override fun toString(): String {
        return "Contractor(id=$id, name='$name', company=$company)"
    }

}