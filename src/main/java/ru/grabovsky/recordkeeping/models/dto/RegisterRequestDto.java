package ru.grabovsky.recordkeeping.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Length(min = 3, message = "Имя пользователя должно содержать минимум 3 символа")
    private String username;
    @NotBlank(message = "Пароль не должен быть пустым")
    @Length(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password;
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Введите корректный адрес электронной почты")
    private String email;
}
