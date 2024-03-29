package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.time.LocalDate

/**
 * Entity for user profile table
 *
 * @author GrabovskyAlexey
 */
@Entity
@Table(name = "user_info")
class UserInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
): BaseEntity(id) {

    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    lateinit var user: User

    @Column(name = "name")
    var name: String? = null

    @Column(name = "surname")
    var surname: String? = null

    @Column(name = "phone")
    var phone: String? = null

    @Column(name = "city")
    var city: String? = null

    @Column(name = "birthday")
    var birthday: LocalDate? = null

    @Column(name = "reg_date", nullable = false)
    lateinit var regDate: LocalDate

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: Instant? = null

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    override fun toString(): String {
        return "UserInfo(id=$id, user=$user, name=$name, surname=$surname, phone=$phone, city=$city, birthday=$birthday, regDate=$regDate)"
    }

}