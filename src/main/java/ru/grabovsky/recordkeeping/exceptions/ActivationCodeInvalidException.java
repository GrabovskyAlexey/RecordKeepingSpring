package ru.grabovsky.recordkeeping.exceptions;

public class ActivationCodeInvalidException extends RuntimeException{
    public ActivationCodeInvalidException(String message) {
        super(message);
    }
}
