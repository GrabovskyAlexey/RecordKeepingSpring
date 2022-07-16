package ru.grabovsky.recordkeeping.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import ru.grabovsky.recordkeeping.models.types.Direction;
import ru.grabovsky.recordkeeping.models.types.PostgreSQLEnumType;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Entity for record of incoming or outgoing documents
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "records")
@TypeDef(name = "pg_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@ToString
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "pg_enum")
    @Column(name = "direction", nullable = false)
    private Direction direction;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "mail_number", nullable = false)
    private String mailNumber;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "reply")
    private String reply;

    @Column(name = "reply_to")
    private String replyTo;

    @Column(name = "mail_date", nullable = false)
    private LocalDate mailDate;

    @Column(name = "description")

    private String description;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "record")
    @ToString.Exclude
    private List<File> files;

    public Record() {
        this.regDate = LocalDate.now();
        this.mailDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Record record = (Record) o;
        return id != null && Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}