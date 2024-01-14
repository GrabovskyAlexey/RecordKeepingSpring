package ru.grabovsky.recordkeeping.notification.services.interfaces

import ru.grabovsky.recordkeeping.api.notification.HTMLEmailMessage
import ru.grabovsky.recordkeeping.api.notification.SimpleTextEmailMessage


interface NotificationService {
    /**
     * Send simple text mail to user
     *
     * @param context Simple text message info for send
     */
    fun sendSimpleTextEmail(context: SimpleTextEmailMessage)
    fun sendHTMLEmail(context: HTMLEmailMessage)
}
