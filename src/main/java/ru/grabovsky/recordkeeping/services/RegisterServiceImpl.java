package ru.grabovsky.recordkeeping.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.grabovsky.recordkeeping.context.HTMLEmailContext;
import ru.grabovsky.recordkeeping.exceptions.RequestMustNotBeNullException;
import ru.grabovsky.recordkeeping.exceptions.RoleNotFoundException;
import ru.grabovsky.recordkeeping.exceptions.UserAlreadyExistsException;
import ru.grabovsky.recordkeeping.models.dto.RegisterRequestDto;
import ru.grabovsky.recordkeeping.models.entities.Role;
import ru.grabovsky.recordkeeping.models.entities.User;
import ru.grabovsky.recordkeeping.models.entities.UserInfo;
import ru.grabovsky.recordkeeping.repositories.RoleRepository;
import ru.grabovsky.recordkeeping.repositories.UserInfoRepository;
import ru.grabovsky.recordkeeping.repositories.UserRepository;
import ru.grabovsky.recordkeeping.services.abstaract.MailService;
import ru.grabovsky.recordkeeping.services.abstaract.RegisterService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.sender.address}")
    private String senderAddress;

    @Override
    public void register(RegisterRequestDto request) {
        if (request == null) {
            throw new RequestMustNotBeNullException("Request must not be null");
        }
        checkExistsUsernameOrEmail(request);
        User user = createUser(request);
        UserInfo info = createUserInfo(user);
        HTMLEmailContext emailContext = createActivationEmail(
                user.getEmail(),
                user.getUsername(),
                info.getActivationCode());
        mailService.sendHTMLMail(emailContext);
    }

    private UserInfo createUserInfo(User user) {
        UserInfo info = new UserInfo();
        info.setActivationCode(UUID.randomUUID().toString());
        info.setUser(user);
        info.setRegDate(LocalDate.now(ZoneId.of("UTC")));
        userInfoRepository.save(info);
        return info;
    }

    private User createUser(RegisterRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setActivated(false);
        Set<Role> roles = new HashSet<>();
        roles.add(getUnactivatedRole());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    private void checkExistsUsernameOrEmail(RegisterRequestDto request) {
        boolean existsUsername = userRepository.existsUserByUsernameIgnoreCase(request.getUsername());
        boolean existsEmail = userRepository.existsUserByEmailIgnoreCase(request.getEmail());
        if (existsUsername && existsEmail) {
            throw new UserAlreadyExistsException(String.format(
                    "Имя пользователя '%s' и электронная почта '%s' уже используются", request.getUsername(), request.getEmail()));
        }
        if (existsUsername) {
            throw new UserAlreadyExistsException(String.format(
                    "Имя пользователя '%s' уже используются", request.getUsername()));
        }
        if (existsEmail) {
            throw new UserAlreadyExistsException(String.format(
                    "Электронная почта '%s' уже используются", request.getEmail()));
        }
    }

    private Role getUnactivatedRole() {
        return roleRepository.findByRole("ROLE_UNACTIVATED_USER")
                .orElseThrow(() -> new RoleNotFoundException("ROLE_UNACTIVATED_USER not found"));
    }

    private HTMLEmailContext createActivationEmail(String email, String username, String activationCode){
        HTMLEmailContext context = new HTMLEmailContext();
        context.setTo(email);
        context.setReceiverDisplayName(username);
        context.setTemplateName("activation");
        context.setSubject("Активация аккаунта");
        context.setFrom(senderAddress);
        HashMap<String, Object> templateItems = new HashMap<>();
        templateItems.put("username", username);
        templateItems.put("email", email);
        templateItems.put("activationCode", activationCode);
        context.setContext(templateItems);
        return context;
    }
}
