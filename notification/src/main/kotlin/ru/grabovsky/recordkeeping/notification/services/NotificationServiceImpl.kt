package ru.grabovsky.recordkeeping.notification.services

import freemarker.template.TemplateException
import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.mail.MailParseException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import ru.grabovsky.recordkeeping.api.notification.HTMLEmailMessage
import ru.grabovsky.recordkeeping.api.notification.SimpleTextEmailMessage
import ru.grabovsky.recordkeeping.notification.services.interfaces.NotificationService
import java.io.IOException
import java.nio.charset.StandardCharsets
import ru.grabovsky.recordkeeping.notification.utils.getLogger

@Service
@KafkaListener(topics = ["email"], groupId = "notify")
class NotificationServiceImpl(
    private val mailSender: JavaMailSender,
    private val freemarkerConfigurer: FreeMarkerConfigurer,
    @Value("\${debug}") private val isDebug: Boolean
) : NotificationService {

    private val log = getLogger(javaClass)
    @KafkaHandler
    override fun sendSimpleTextEmail(context: SimpleTextEmailMessage) {
        try {
            log.warn("Simple")
            val message = SimpleMailMessage()
            message.setTo(context.to)
            message.from = context.from
            message.subject = context.subject
            message.text = context.text
            if (isDebug) {
                log.warn("Message prepare sent")
            } else {
                mailSender.send(message)
            }
        } catch (e: MailParseException) {
            throw RuntimeException(e.message)
        }
    }

    @KafkaHandler
    override fun sendHTMLEmail(context: HTMLEmailMessage) {
        try {
            log.warn("HTML")
            val mimeMessage: MimeMessage = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
            )

            val freemarkerTemplate = freemarkerConfigurer.configuration
                .getTemplate(context.templateName + ".ftl")
            val htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, context.context)
            helper.setTo(context.to)
            helper.setSubject(context.subject)
            helper.setFrom(context.from)
            helper.setText(htmlBody, true)
            if (isDebug) {
                log.warn("Message prepare sent")
            } else {
                mailSender.send(mimeMessage)
            }
        } catch (e: TemplateException) {
            log.error("Ошибка обработки шаблона: ${e.message}")
        } catch (e: MessagingException) {
            log.error("Ошибка подготовки шаблона: ${e.message}")
        } catch (e: IOException) {
            log.error("Не найден шаблон ${context.templateName}. Отправка прервана: ${e.message}")
        }
    }
}
