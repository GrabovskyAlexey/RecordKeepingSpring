package ru.grabovsky.recordkeeping.api.notification

/**
 * Info to create HTML email message
 *
 * @author GrabovskyAlexey
 */

class HTMLEmailMessage : EmailMessage() {
    lateinit var templateName: String
    val context: MutableMap<String, Any> = mutableMapOf()
}
