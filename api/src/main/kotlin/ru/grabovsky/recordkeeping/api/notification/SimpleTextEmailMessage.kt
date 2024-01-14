package ru.grabovsky.recordkeeping.api.notification

/**
 * Info to create simple text email message
 *
 * @author GrabovskyAlexey
 */
class SimpleTextEmailMessage : EmailMessage() {
    lateinit var text: String
}
