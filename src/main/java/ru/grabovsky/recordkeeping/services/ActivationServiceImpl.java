package ru.grabovsky.recordkeeping.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grabovsky.recordkeeping.exceptions.AccountAlreadyActivatedException;
import ru.grabovsky.recordkeeping.exceptions.ActivationCodeInvalidException;
import ru.grabovsky.recordkeeping.exceptions.RoleNotFoundException;
import ru.grabovsky.recordkeeping.exceptions.UserNotFoundException;
import ru.grabovsky.recordkeeping.models.dto.ActivationRequestDto;
import ru.grabovsky.recordkeeping.models.entities.Role;
import ru.grabovsky.recordkeeping.models.entities.User;
import ru.grabovsky.recordkeeping.repositories.RoleRepository;
import ru.grabovsky.recordkeeping.repositories.UserRepository;
import ru.grabovsky.recordkeeping.services.abstaract.ActivationService;

@Service
@RequiredArgsConstructor
public class ActivationServiceImpl implements ActivationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public void activate(ActivationRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException(String.format("Пользователь с адресом электронной почты '%s' не найден", request.getEmail())));
        if(user.isActivated()){
            throw new AccountAlreadyActivatedException("Данный адрес электронной почты уже подтвержден");
        }
        if(!user.getUserInfo().getActivationCode().equals(request.getCode())){
            throw new ActivationCodeInvalidException("Указан не правильный код активации");
        }
        user.setActivated(true);
        user.getRoles().clear();
        user.getRoles().add(getRegisteredRole());
        userRepository.save(user);
    }

    private Role getRegisteredRole() {
        return roleRepository.findByRole("ROLE_REGISTERED_USER")
                .orElseThrow(() -> new RoleNotFoundException("ROLE_REGISTERED_USER not found"));
    }

}
