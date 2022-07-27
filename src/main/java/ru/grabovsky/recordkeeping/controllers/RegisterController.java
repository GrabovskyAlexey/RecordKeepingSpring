package ru.grabovsky.recordkeeping.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.recordkeeping.models.dto.AuthResponseDto;
import ru.grabovsky.recordkeeping.models.dto.RegisterRequestDto;
import ru.grabovsky.recordkeeping.services.abstaract.RegisterService;
import ru.grabovsky.recordkeeping.services.abstaract.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        registerService.register(request);
        return ResponseEntity.ok(
                new AuthResponseDto(
                        userService.getTokenByUsername(request.getUsername())
                )
        );
    }
}
