package ru.grabovsky.recordkeeping.models.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Entity for company info table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Contractor> contractors;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Employee> employees;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Invite> invites;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Project> projects;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Record> records;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<User> users;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return id != null && Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}