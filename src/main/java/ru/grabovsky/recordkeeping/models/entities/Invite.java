package ru.grabovsky.recordkeeping.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

/**
 * Entity for company invite table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "invites")
@Getter
@Setter
@ToString
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "invite_code", nullable = false)
    private String inviteCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Invite invite = (Invite) o;
        return id != null && Objects.equals(id, invite.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}