package ru.grabovsky.recordkeeping.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity for user profile table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "user_info")
@Getter
@Setter
@ToString
public class UserInfo {
    @Id
    private Long id;

    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "activation_code")
    private String activationCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserInfo userInfo = (UserInfo) o;
        return id != null && Objects.equals(id, userInfo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}