package ru.grabovsky.recordkeeping.api.notification

sealed class EmailMessage(open val from: String,
                          open val senderDisplayName: String,
                          open val to: String,
                          open val receiverDisplayName: String,
                          open val subject: String) {

}