package ru.grabovsky.recordkeeping.exceptions;

public class MailTemplateNotFoundException extends RuntimeException{
    public MailTemplateNotFoundException(String message) {
        super(message);
    }
}
