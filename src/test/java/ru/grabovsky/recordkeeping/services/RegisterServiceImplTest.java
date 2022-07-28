package ru.grabovsky.recordkeeping.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.grabovsky.recordkeeping.configs.MailConfigTest;
import ru.grabovsky.recordkeeping.exceptions.RequestMustNotBeNullException;
import ru.grabovsky.recordkeeping.exceptions.UserAlreadyExistsException;
import ru.grabovsky.recordkeeping.models.dto.RegisterRequestDto;
import ru.grabovsky.recordkeeping.models.entities.Role;
import ru.grabovsky.recordkeeping.repositories.RoleRepository;
import ru.grabovsky.recordkeeping.repositories.UserInfoRepository;
import ru.grabovsky.recordkeeping.repositories.UserRepository;
import ru.grabovsky.recordkeeping.services.abstaract.MailService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MailConfigTest.class})
class RegisterServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserInfoRepository userInfoRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private MailService mailService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private RegisterServiceImpl registerService;

    @BeforeEach
    void setUp() {
        registerService = new RegisterServiceImpl(userRepository,userInfoRepository,roleRepository,mailService, passwordEncoder);
    }

    @Test
    void should_successful_register() {
        Role role = new Role();
        role.setRole("ROLE_UNACTIVATED_USER");
        Mockito.when(roleRepository.findByRole("ROLE_UNACTIVATED_USER")).thenReturn(Optional.of(role));
        RegisterRequestDto dto = new RegisterRequestDto();
        dto.setUsername("user");
        dto.setEmail("test@test.ru");
        dto.setPassword("password");
        assertThatNoException().isThrownBy(()-> registerService.register(dto));
    }

    @Test
    void should_error_on_already_register_user(){
        Mockito.when(userRepository.existsUserByUsernameIgnoreCase("user")).thenReturn(true);
        Mockito.when(userRepository.existsUserByEmailIgnoreCase("test@test.ru")).thenReturn(true);
        RegisterRequestDto dtoUsernameAndEmail = new RegisterRequestDto();
        dtoUsernameAndEmail.setUsername("user");
        dtoUsernameAndEmail.setEmail("test@test.ru");
        dtoUsernameAndEmail.setPassword("password");
        RegisterRequestDto dtoUsername = new RegisterRequestDto();
        dtoUsername.setUsername("user");
        dtoUsername.setEmail("test1@test.ru");
        dtoUsername.setPassword("password");
        RegisterRequestDto dtoEmail = new RegisterRequestDto();
        dtoEmail.setUsername("user1");
        dtoEmail.setEmail("test@test.ru");
        dtoEmail.setPassword("password");

        assertThatExceptionOfType(UserAlreadyExistsException.class)
                .isThrownBy(()->registerService.register(dtoUsernameAndEmail))
                .withMessage("Имя пользователя 'user' и электронная почта 'test@test.ru' уже используются");

        assertThatExceptionOfType(UserAlreadyExistsException.class)
                .isThrownBy(()->registerService.register(dtoUsername))
                .withMessage("Имя пользователя 'user' уже используются");

        assertThatExceptionOfType(UserAlreadyExistsException.class)
                .isThrownBy(()->registerService.register(dtoEmail))
                .withMessage("Электронная почта 'test@test.ru' уже используются");
    }

    @Test
    void should_error_null_request(){
        assertThatExceptionOfType(RequestMustNotBeNullException.class)
                .isThrownBy(()->registerService.register(null))
                .withMessage("Request must not be null");
    }
}