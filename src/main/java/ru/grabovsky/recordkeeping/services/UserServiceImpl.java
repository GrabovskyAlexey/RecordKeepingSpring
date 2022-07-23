package ru.grabovsky.recordkeeping.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grabovsky.recordkeeping.models.entities.Authority;
import ru.grabovsky.recordkeeping.models.entities.Role;
import ru.grabovsky.recordkeeping.models.entities.User;
import ru.grabovsky.recordkeeping.repositories.UserRepository;
import ru.grabovsky.recordkeeping.services.abstaract.UserService;
import ru.grabovsky.recordkeeping.utils.JwtTokenUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getUserRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getUserRoles(Collection<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return new HashSet<SimpleGrantedAuthority>();
        }
        return getRolesAndPrivileges(roles)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private Collection<String> getRolesAndPrivileges(Collection<Role> roles) {
        Set<String> result = new HashSet<>();
        for (Role role : roles) {
            result.add(role.getRole());
            result.addAll(
                    role.getAuthorities()
                            .stream()
                            .map(Authority::getAuthority)
                            .collect(Collectors.toSet()));
        }
        return result;
    }

    @Override
    public String getTokenByUsername(String username) {
        return jwtTokenUtil.generateToken(loadUserByUsername(username));
    }
}
