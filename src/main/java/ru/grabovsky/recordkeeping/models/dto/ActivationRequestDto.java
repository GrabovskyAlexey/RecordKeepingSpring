package ru.grabovsky.recordkeeping.models.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ActivationRequestDto {
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Введите корректный адрес электронной почты")
    private String email;
    @NotBlank(message = "Код активации не может быть пустым")
    private String code;
}
