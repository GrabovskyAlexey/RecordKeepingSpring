package ru.grabovsky.recordkeeping.core.exceptions.handlers

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.grabovsky.recordkeeping.api.dto.utils.MessageDto
import ru.grabovsky.recordkeeping.api.dto.validations.ValidationError
import ru.grabovsky.recordkeeping.api.dto.validations.ValidationErrorResponseDto
import ru.grabovsky.recordkeeping.core.exceptions.BadRequestException
import ru.grabovsky.recordkeeping.core.exceptions.ForbiddenOperationException
import ru.grabovsky.recordkeeping.core.exceptions.NotFoundException
import ru.grabovsky.recordkeeping.core.utils.getLogger
import java.util.stream.Collectors

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = getLogger(javaClass)

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun catchBadRequestException(e: BadRequestException): MessageDto {
        log.warn("Catch exception ${e.javaClass.simpleName} Message: ${e.message}")
        return MessageDto(e.message!!)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun catchNotFoundException(e: NotFoundException): MessageDto {
        log.warn("Catch exception ${e.javaClass.simpleName} Message: ${e.message}")
        return MessageDto(e.message!!)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun catchForbiddenOperationException(e: ForbiddenOperationException): MessageDto {
        log.warn("Catch exception ${e.javaClass.simpleName} Message: ${e.message}")
        return MessageDto(e.message!!)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onMethodArgumentNotValidException(e: MethodArgumentNotValidException): ValidationErrorResponseDto {
        val errors = e.bindingResult.fieldErrors.stream()
            .map { error: FieldError -> ValidationError(error.field, error.defaultMessage ?: "") }
            .collect(Collectors.toList())
        return ValidationErrorResponseDto(errors)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onConstraintValidationException(e: ConstraintViolationException): ValidationErrorResponseDto {
        val violations = e.constraintViolations.stream()
            .map { error: ConstraintViolation<*> -> ValidationError(error.propertyPath.toString(), error.message) }
            .collect(Collectors.toList())
        return ValidationErrorResponseDto(violations)
    }
}
