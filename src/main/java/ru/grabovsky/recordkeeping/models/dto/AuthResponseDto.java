package ru.grabovsky.recordkeeping.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class AuthResponseDto {
    private String token;
    private Instant time = Instant.now();

    public AuthResponseDto(String token) {
        this.token = token;
    }
}
