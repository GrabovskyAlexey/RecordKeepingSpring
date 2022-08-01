package ru.grabovsky.recordkeeping.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.recordkeeping.models.dto.ActivationRequestDto;
import ru.grabovsky.recordkeeping.models.dto.ActivationStatusDto;
import ru.grabovsky.recordkeeping.models.types.OperationStatus;
import ru.grabovsky.recordkeeping.services.abstaract.ActivationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/activation")
@RequiredArgsConstructor
public class ActivationController {
    private final ActivationService activationService;
    @PostMapping("")
    public ResponseEntity<ActivationStatusDto> activate(@Valid @RequestBody ActivationRequestDto request){
        System.out.println("Activate");
        activationService.activate(request);
        return new ResponseEntity<>(new ActivationStatusDto(OperationStatus.SUCCESS, "Адрес электронной почты подтвержден"), HttpStatus.OK);
    }
}
