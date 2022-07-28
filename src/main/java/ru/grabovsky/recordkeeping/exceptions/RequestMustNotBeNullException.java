package ru.grabovsky.recordkeeping.exceptions;

public class RequestMustNotBeNullException extends RuntimeException{
    public RequestMustNotBeNullException(String message) {
        super(message);
    }
}
