package ru.grabovsky.recordkeeping.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class MessageDto {
    private String message;
    private Instant time = Instant.now();

    public MessageDto(String message) {
        this.message = message;
    }
}
