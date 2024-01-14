package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.JdbcType
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.dialect.PostgreSQLEnumJdbcType
import ru.grabovsky.recordkeeping.core.entity.types.Direction
import java.time.Instant
import java.time.LocalDate

/**
 * Entity for record of incoming or outgoing documents
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "records")
class Record(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType::class)
    @Column(columnDefinition = "direction", nullable = false)
    lateinit var direction: Direction

    @Column(name = "color", nullable = false)
    lateinit var color: String

    @Column(name = "mail_number", nullable = false)
    lateinit var mailNumber: String

    @Column(name = "reg_date", nullable = false)
    lateinit var regDate: LocalDate

    @Column(name = "title", nullable = false)
    lateinit var title: String

    @Column(name = "reply")
    var reply: String? = null

    @Column(name = "reply_to")
    var replyTo: String? = null

    @Column(name = "mail_date", nullable = false)
    lateinit var mailDate: LocalDate

    @Column(name = "description")
    var description: String? = null

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @ManyToOne
    @JoinColumn(name = "project_id")
    var project: Project? = null

    @ManyToOne
    @JoinColumn(name = "employee_id")
    var employee: Employee? = null

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    var contractor: Contractor? = null

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    lateinit var company: Company

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    lateinit var author: User

    @OneToMany(mappedBy = "record")
    val files: List<File> = listOf()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        val record = other as Record
        return id != null && id == record.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Record(id=$id, direction=$direction, color='$color', mailNumber='$mailNumber', regDate=$regDate, title='$title', reply=$reply, replyTo=$replyTo, mailDate=$mailDate, description=$description, project=$project, employee=$employee, contractor=$contractor, company=$company, author=$author)"
    }
}