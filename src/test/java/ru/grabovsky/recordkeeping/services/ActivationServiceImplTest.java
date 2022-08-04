package ru.grabovsky.recordkeeping.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.grabovsky.recordkeeping.exceptions.AccountAlreadyActivatedException;
import ru.grabovsky.recordkeeping.exceptions.ActivationCodeInvalidException;
import ru.grabovsky.recordkeeping.exceptions.UserNotFoundException;
import ru.grabovsky.recordkeeping.models.dto.ActivationRequestDto;
import ru.grabovsky.recordkeeping.models.entities.Role;
import ru.grabovsky.recordkeeping.models.entities.User;
import ru.grabovsky.recordkeeping.models.entities.UserInfo;
import ru.grabovsky.recordkeeping.repositories.RoleRepository;
import ru.grabovsky.recordkeeping.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ActivationServiceImplTest {
    private ActivationServiceImpl activationService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        activationService = new ActivationServiceImpl(userRepository, roleRepository);
        Role role = new Role();
        role.setRole("ROLE_REGISTERED_USER");
        Mockito.when(roleRepository.findByRole("ROLE_REGISTERED_USER")).thenReturn(Optional.of(role));
    }

    @Test
    void should_success_activate(){
        ActivationRequestDto request = new ActivationRequestDto();
        request.setEmail("test@test.ru");
        request.setCode("94a9e463-3dff-40dd-b487-b5b56a56ab5a");
        Mockito.when(userRepository.findByEmail("test@test.ru")).thenReturn(Optional.of(getUnactivatedUser()));
        assertThatNoException().isThrownBy(()-> activationService.activate(request));
    }

    @Test
    void should_error_incorrect_email(){
        ActivationRequestDto request = new ActivationRequestDto();
        request.setEmail("test@test.ru");
        request.setCode("94a9e463-3dff-40dd-b487-b5b56a56ab5a");
        Mockito.when(userRepository.findByEmail("test@test.ru")).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(()-> activationService.activate(request));
    }

    @Test
    void should_error_incorrect_activation_code(){
        ActivationRequestDto request = new ActivationRequestDto();
        request.setEmail("test@test.ru");
        request.setCode("94a9e463-3dff-40dd-b487-b5b56a56ab5b");
        Mockito.when(userRepository.findByEmail("test@test.ru")).thenReturn(Optional.of(getUnactivatedUser()));
        assertThatExceptionOfType(ActivationCodeInvalidException.class).isThrownBy(()-> activationService.activate(request));
    }

    @Test
    void should_error_already_activated_account(){
        ActivationRequestDto request = new ActivationRequestDto();
        request.setEmail("test@test.ru");
        request.setCode("94a9e463-3dff-40dd-b487-b5b56a56ab5b");
        Mockito.when(userRepository.findByEmail("test@test.ru")).thenReturn(Optional.of(getActivatedUser()));
        assertThatExceptionOfType(AccountAlreadyActivatedException.class).isThrownBy(()-> activationService.activate(request));
    }


    private User getUnactivatedUser(){
        User user = new User();
        UserInfo info = new UserInfo();
        user.setEmail("test@test.ru");
        user.setRoles(new HashSet<>());
        user.setActivated(false);
        info.setActivationCode("94a9e463-3dff-40dd-b487-b5b56a56ab5a");
        user.setUserInfo(info);
        return user;
    }

    private User getActivatedUser(){
        User user = new User();
        UserInfo info = new UserInfo();
        user.setEmail("test@test.ru");
        user.setRoles(new HashSet<>());
        user.setActivated(true);
        info.setActivationCode("94a9e463-3dff-40dd-b487-b5b56a56ab5a");
        user.setUserInfo(info);
        return user;
    }

}