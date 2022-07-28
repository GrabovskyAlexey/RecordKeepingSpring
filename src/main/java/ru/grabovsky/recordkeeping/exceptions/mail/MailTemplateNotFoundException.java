package ru.grabovsky.recordkeeping.exceptions.mail;

public class MailTemplateNotFoundException extends RuntimeException{
    public MailTemplateNotFoundException(String message) {
        super(message);
    }
}
