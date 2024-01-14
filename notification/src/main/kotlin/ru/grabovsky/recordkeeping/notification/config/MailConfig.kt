package ru.grabovsky.recordkeeping.notification.config

import freemarker.cache.ClassTemplateLoader
import freemarker.cache.TemplateLoader
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer

/**
 * Configuration required bean for email service
 *
 * @author GrabovskyAlexey
 */
@Configuration
class MailConfig(
    @Value("\${spring.mail.templates.path}")
    private val mailTemplatesPath: String
) {
    @Bean
    fun freemarkerClassLoaderConfig(): FreeMarkerConfigurer {
        val configuration = freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27)
        val templateLoader: TemplateLoader = ClassTemplateLoader(this.javaClass, mailTemplatesPath)
        configuration.templateLoader = templateLoader
        val freeMarkerConfigurer = FreeMarkerConfigurer()
        freeMarkerConfigurer.configuration = configuration
        return freeMarkerConfigurer
    }
}
