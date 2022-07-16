package ru.grabovsky.recordkeeping.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

/**
 * Entity for logging user action
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name="user_logs")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action")
    private String action;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserLog userLog = (UserLog) o;
        return id != null && Objects.equals(id, userLog.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
