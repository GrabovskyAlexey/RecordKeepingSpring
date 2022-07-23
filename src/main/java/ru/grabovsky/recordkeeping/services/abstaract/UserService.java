package ru.grabovsky.recordkeeping.services.abstaract;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String getTokenByUsername(String username);
}
