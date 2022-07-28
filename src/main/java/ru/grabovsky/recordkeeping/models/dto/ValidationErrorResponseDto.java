package ru.grabovsky.recordkeeping.models.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponseDto {
    private final List<ValidationError> errors;
    private final Instant time = Instant.now();
}
