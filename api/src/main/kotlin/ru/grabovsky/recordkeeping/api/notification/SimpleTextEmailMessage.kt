package ru.grabovsky.recordkeeping.api.notification

/**
 * Info to create simple text email message
 *
 * @author GrabovskyAlexey
 */
data class SimpleTextEmailMessage(
    val text: String,
    override val from: String,
    override val senderDisplayName: String,
    override val to: String,
    override val receiverDisplayName: String,
    override val subject: String
) : EmailMessage(from, senderDisplayName, to, receiverDisplayName, subject) {}
