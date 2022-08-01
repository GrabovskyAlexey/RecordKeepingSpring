package ru.grabovsky.recordkeeping.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grabovsky.recordkeeping.models.types.OperationStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ActivationStatusDto {
    private OperationStatus status;
    private Instant time = Instant.now();
    private String message;

    public ActivationStatusDto(OperationStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
