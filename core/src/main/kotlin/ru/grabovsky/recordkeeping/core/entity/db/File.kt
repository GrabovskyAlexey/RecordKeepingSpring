package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

/**
 * Entity for record related files
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "files")
class File (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {

    @Column(name = "path", nullable = false)
    lateinit var path: String

    @Column(name = "filename", nullable = false)
    lateinit var filename: String

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    lateinit var record: Record

    override fun toString(): String {
        return "File(id=$id, path='$path', filename='$filename', record=$record)"
    }
}