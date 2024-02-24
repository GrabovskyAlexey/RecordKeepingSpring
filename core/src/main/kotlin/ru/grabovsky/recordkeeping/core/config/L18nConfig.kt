package ru.grabovsky.recordkeeping.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

/**
 *
 *
 * @author GrabovskyAlexey
 * @created 23.02.2024 12:12
 */

@Configuration
class L18nConfig {
    @Bean
    fun localeResolver(): LocaleResolver =
        with(AcceptHeaderLocaleResolver()) {
            setDefaultLocale(Locale.of("ru", "RU"))
            this
        }

    @Bean
    fun messageSource(): ResourceBundleMessageSource =
        with(ResourceBundleMessageSource()) {
            setUseCodeAsDefaultMessage(true)
            setBasenames("language/Messages", "language/ValidationMessages", "language/ErrorMessages", "language/Swagger")
            setDefaultEncoding("UTF-8")
            this
        }
}