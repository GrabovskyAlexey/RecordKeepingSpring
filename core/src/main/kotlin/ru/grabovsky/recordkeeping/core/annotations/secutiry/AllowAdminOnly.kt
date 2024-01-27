package ru.grabovsky.recordkeeping.core.annotations.secutiry

import org.springframework.security.access.annotation.Secured

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 27.01.2024 18:13
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Secured("ROLE_APP_ADMIN")
annotation class AllowAdminOnly()
