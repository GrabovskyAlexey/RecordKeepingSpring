package ru.grabovsky.recordkeeping.models.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequestDto {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
