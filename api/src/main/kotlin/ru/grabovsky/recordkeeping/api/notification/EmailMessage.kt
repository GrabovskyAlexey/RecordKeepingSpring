package ru.grabovsky.recordkeeping.api.notification

sealed class EmailMessage(
) {
    open lateinit var from: String
    open lateinit var senderDisplayName: String
    open lateinit var to: String
    open lateinit var receiverDisplayName: String
    open lateinit var subject: String
}