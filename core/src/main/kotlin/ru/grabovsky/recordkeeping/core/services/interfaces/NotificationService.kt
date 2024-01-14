package ru.grabovsky.recordkeeping.core.services.interfaces

import ru.grabovsky.recordkeeping.api.notification.EmailType
import ru.grabovsky.recordkeeping.api.utils.ConfirmToken
import ru.grabovsky.recordkeeping.core.entity.db.User

/**
 *
 *
 * @author GrabovskyAlexey
 * @date 05.01.2024
 */
interface NotificationService {
    fun sendSimpleEmail(user: User, code: String, type: EmailType)
    fun sendHTMLEmail(user: User, code: String, type: EmailType)
    fun sendTokenInHtmlEmail(token: ConfirmToken)
    fun sendTokenInSimpleTextEmail(token: ConfirmToken)
}