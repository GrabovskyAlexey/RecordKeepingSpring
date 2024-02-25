package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.JdbcType
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.dialect.PostgreSQLEnumJdbcType
import ru.grabovsky.recordkeeping.api.types.Direction
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
    override var id: Long? = null
): BaseEntity(id) {

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType::class)
    @Column(columnDefinition = "direction", nullable = false)
    lateinit var direction: Direction

    @Column(name = "color", nullable = false)
    var color: String = "#FFFFFF"

    @Column(name = "mail_number", nullable = false)
    lateinit var mailNumber: String

    @Column(name = "reg_date", nullable = false)
    lateinit var regDate: LocalDate

    @Column(name = "subject", nullable = false)
    lateinit var subject: String

    @OneToMany(mappedBy = "replyTo", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Column(name = "reply_id")
    val reply: Set<Record> = setOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to_id")
    var replyTo: Record? = null

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
    @JoinColumn(name = "organization_id", nullable = false)
    lateinit var organization: Organization

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    lateinit var author: User

    @OneToMany(mappedBy = "record")
    val files: List<File> = listOf()

    override fun toString(): String {
        return "Record(id=$id, direction=$direction, color='$color', mailNumber='$mailNumber', regDate=$regDate, subject='$subject', reply=$reply, replyTo=$replyTo, mailDate=$mailDate, description=$description, project=$project, employee=$employee, contractor=$contractor, organization=$organization, author=$author)"
    }
}