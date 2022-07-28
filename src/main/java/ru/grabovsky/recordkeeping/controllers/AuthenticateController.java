package ru.grabovsky.recordkeeping.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.recordkeeping.models.dto.AuthRequestDto;
import ru.grabovsky.recordkeeping.models.dto.AuthResponseDto;
import ru.grabovsky.recordkeeping.services.abstaract.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<AuthResponseDto> createAuthToken(@Valid @RequestBody AuthRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(
                new AuthResponseDto(
                        userService.getTokenByUsername(request.getUsername())
                )
        );
    }
}
