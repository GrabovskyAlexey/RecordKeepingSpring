package ru.grabovsky.recordkeeping.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError {
    private String fieldName;
    private String message;
}
