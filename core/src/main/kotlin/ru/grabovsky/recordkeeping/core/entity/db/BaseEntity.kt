package ru.grabovsky.recordkeeping.core.entity.db

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.Hibernate
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 08.01.2024
 */
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null
) {
    val isNew get() = id == null

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) return false
        val other = obj as BaseEntity
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }
}