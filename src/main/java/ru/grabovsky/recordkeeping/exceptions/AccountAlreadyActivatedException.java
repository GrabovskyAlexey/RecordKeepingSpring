package ru.grabovsky.recordkeeping.exceptions;

public class AccountAlreadyActivatedException extends RuntimeException{
    public AccountAlreadyActivatedException(String message) {
        super(message);
    }
}
