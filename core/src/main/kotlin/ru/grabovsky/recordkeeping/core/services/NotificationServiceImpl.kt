package ru.grabovsky.recordkeeping.core.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.grabovsky.recordkeeping.api.notification.EmailType
import ru.grabovsky.recordkeeping.api.notification.HTMLEmailMessage
import ru.grabovsky.recordkeeping.api.notification.SimpleTextEmailMessage
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken.TokenType
import ru.grabovsky.recordkeeping.core.entity.db.User
import ru.grabovsky.recordkeeping.core.exceptions.IncorrectEmailTypeException
import ru.grabovsky.recordkeeping.core.services.interfaces.NotificationService
import ru.grabovsky.recordkeeping.core.utils.JwtTokenUtil

@Service
class NotificationServiceImpl(
    @Value("\${application.mail.url}") private val url: String,
    @Value("\${application.mail.sender-email}") private val emailFrom: String,
    @Value("\${application.mail.sender-name}") private val senderName: String,
    private val jwtTokenUtil: JwtTokenUtil,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : NotificationService {
    override fun sendSimpleEmail(user: User, code: String, type: EmailType) {
        val message = SimpleTextEmailMessage()
        message.from = emailFrom
        message.senderDisplayName = senderName
        message.to = user.email
        message.receiverDisplayName = user.username
        message.subject = type.subject
        message.text = when (type) {
            EmailType.ACTIVATE -> {
                """
                    Добро пожаловать, ${user.username}
                    Для подтверждения адреса электронной почты необходимо пройти по ссылке:
                    ${url}/activation?email=${user.email}&code=${code}
                """.trimIndent()
            }

            EmailType.RESET_PASSWORD -> {
                """
                    ${user.username}, для вашего аккаунта был запрошен сброс пароля
                    Для подтверждения  необходимо пройти по ссылке:
                    ${url}/reset-password?email=${user.email}&code=${code}
                """.trimIndent()
            }

            EmailType.CHANGE_PASSWORD -> {
                """
                    ${user.username}, для вашего аккаунта была запрошена смена пароля
                    Для подтверждения  необходимо пройти по ссылке:
                    ${url}/change-password?email=${user.email}&code=${code}
                """.trimIndent()
            }

            EmailType.NEW_PASSWORD -> {
                """
                    ${user.username}, пароль был изменен.
                    Новый пароль: $code
                """.trimIndent()
            }

            EmailType.INVITE -> {
                """
                    
                """.trimIndent()
            }
        }
        kafkaTemplate.send("email", message)
    }

    override fun sendHTMLEmail(user: User, code: String, type: EmailType) {
        val message = HTMLEmailMessage()
        message.from = emailFrom
        message.senderDisplayName = senderName
        message.to = user.email
        message.receiverDisplayName = user.username
        message.subject = type.subject
        message.templateName = type.template
        message.context["email"] = user.email
        message.context["username"] = user.username
        message.context["code"] = code
        message.context["url"] = url
        kafkaTemplate.send("email", message)
    }

    override fun sendTokenInHtmlEmail(token: ConfirmToken) {
        val type = mapTokenTypeToEmailType(token.type)
        val message = HTMLEmailMessage()
        message.from = emailFrom
        message.senderDisplayName = senderName
        message.to = token.email ?: ""
        message.receiverDisplayName = token.username ?: ""
        message.subject = type.subject
        message.templateName = type.template
        message.context["username"] = token.username ?: ""
        message.context["token"] = jwtTokenUtil.generateConfirmToken(token)
        message.context["url"] = url
        kafkaTemplate.send("email", message)
    }

    override fun sendTokenInSimpleTextEmail(token: ConfirmToken) {
        val type = mapTokenTypeToEmailType(token.type)
        val message = SimpleTextEmailMessage()
        message.from = emailFrom
        message.senderDisplayName = senderName
        message.to = token.email ?: ""
        message.receiverDisplayName = token.username ?: ""
        message.subject = type.subject
        val code = jwtTokenUtil.generateConfirmToken(token)
        message.text = when (type) {
            EmailType.ACTIVATE -> {
                """
                    Добро пожаловать, ${message.receiverDisplayName}
                    Для подтверждения адреса электронной почты необходимо пройти по ссылке:
                    ${url}/confirm-email?token=${code}
                """.trimIndent()
            }

            EmailType.RESET_PASSWORD -> {
                """
                    ${message.receiverDisplayName}, для вашего аккаунта был запрошен сброс пароля
                    Для подтверждения  необходимо пройти по ссылке:
                    ${url}/reset-password?token=${code}
                """.trimIndent()
            }

            EmailType.CHANGE_PASSWORD -> {
                """
                    ${message.receiverDisplayName}, для вашего аккаунта была запрошена смена пароля
                    Для подтверждения  необходимо пройти по ссылке:
                    ${url}/change-password?token=${code}
                """.trimIndent()
            }

            EmailType.NEW_PASSWORD -> {
                """
                    ${message.receiverDisplayName}, пароль был изменен.
                    Новый пароль: $code
                """.trimIndent()
            }

            EmailType.INVITE -> {
                """
                    
                """.trimIndent()
            }
        }
        kafkaTemplate.send("email", message)
    }

    private fun mapTokenTypeToEmailType(type: TokenType?): EmailType {
        return when(type) {
            TokenType.EMAIL_CONFIRM -> EmailType.ACTIVATE
            TokenType.RESET_PASSWORD -> EmailType.RESET_PASSWORD
            TokenType.INVITE -> EmailType.INVITE
            else -> throw IncorrectEmailTypeException("Неизвестный тип сообщения электронной почты")
        }
    }
}