package ru.grabovsky.recordkeeping.exceptions.mail;

public class ProcessMailTemplateException extends RuntimeException{
    public ProcessMailTemplateException(String message) {
        super(message);
    }
}
