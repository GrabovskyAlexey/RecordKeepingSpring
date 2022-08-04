package ru.grabovsky.recordkeeping.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.grabovsky.recordkeeping.exceptions.AccountAlreadyActivatedException;
import ru.grabovsky.recordkeeping.exceptions.ActivationCodeInvalidException;
import ru.grabovsky.recordkeeping.exceptions.UserAlreadyExistsException;
import ru.grabovsky.recordkeeping.exceptions.UserNotFoundException;
import ru.grabovsky.recordkeeping.models.dto.ActivationStatusDto;
import ru.grabovsky.recordkeeping.models.dto.MessageDto;
import ru.grabovsky.recordkeeping.models.dto.ValidationError;
import ru.grabovsky.recordkeeping.models.dto.ValidationErrorResponseDto;
import ru.grabovsky.recordkeeping.models.types.OperationStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponseDto onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponseDto(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageDto onBadCredentialsException(BadCredentialsException e){
        return new MessageDto(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageDto onUserAlreadyExistsException(UserAlreadyExistsException e){
        return new MessageDto(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageDto onUserNotFoundException(UserNotFoundException e){
        return new MessageDto(e.getMessage());
    }

    @ExceptionHandler(AccountAlreadyActivatedException.class)
    public ActivationStatusDto onAccountAlreadyActivatedException(AccountAlreadyActivatedException e){
        return new ActivationStatusDto(OperationStatus.ERROR, e.getMessage());
    }

    @ExceptionHandler(ActivationCodeInvalidException.class)
    public ActivationStatusDto onActivationCodeInvalidException(ActivationCodeInvalidException e){
        return new ActivationStatusDto(OperationStatus.ERROR, e.getMessage());
    }
}
